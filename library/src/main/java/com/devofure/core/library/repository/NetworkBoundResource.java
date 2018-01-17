package com.devofure.core.library.repository;
/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.devofure.core.library.domain.Resource;
import com.devofure.core.library.util.AppExecutors;

import java.util.Objects;

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 * <p>
 * You can read more about it in the <a href="https://developer.android.com/arch">Architecture
 * Guide</a>.
 *
 * @param <ResultType>
 * @param <RequestType>
 */
@SuppressWarnings("WeakerAccess")
public abstract class NetworkBoundResource<ResultType, RequestType> {
	private final AppExecutors appExecutors;

	private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

	@MainThread
	NetworkBoundResource(AppExecutors appExecutors) {
		this.appExecutors = appExecutors;
		result.setValue(Resource.loading(null));
		LiveData<ResultType> dbSource = loadFromDb();
		result.addSource(dbSource, data -> {
			result.removeSource(dbSource);
			if (shouldFetch(data)) {
				fetchFromNetwork(dbSource);
			} else {
				result.addSource(dbSource, newData -> setValue(Resource.success(newData, newData)));
			}
		});
	}

	@NonNull
	@MainThread
	protected abstract LiveData<ResultType> loadFromDb();

	@MainThread
	protected abstract boolean shouldFetch(@Nullable ResultType data);

	private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
		LiveData<Resource<RequestType>> apiResponse = createCall();
		// we re-attach dbSource as a new source, it will dispatch its latest value quickly
		result.addSource(dbSource, newData -> setValue(Resource.loading(newData)));
		result.addSource(apiResponse, response -> {
			result.removeSource(apiResponse);
			result.removeSource(dbSource);
			//noinspection ConstantConditions
			if (response.isSuccessful()) {
				appExecutors.diskIO().execute(() -> {
					saveCallResult(response.data);
					appExecutors.mainThread().execute(() ->
							// we specially request a new live data,
							// otherwise we will get immediately last cached value,
							// which may not be updated with latest results received from network.
							result.addSource(loadFromDb(),
									newData -> setValue(Resource.success(newData, newData)))
					);
				});
			} else {
				onFetchFailed();
				result.addSource(dbSource,
						newData -> setValue(Resource.error(response.message, newData, newData)));
			}
		});
	}

	@MainThread
	private void setValue(Resource<ResultType> newValue) {
		if (!Objects.equals(result.getValue(), newValue)) {
			result.setValue(newValue);
		}
	}

	@NonNull
	@MainThread
	protected abstract LiveData<Resource<RequestType>> createCall();

	@WorkerThread
	protected abstract void saveCallResult(@NonNull RequestType item);

	/**
	 * Here are the use cases and other critical functions
	 */

	protected void onFetchFailed() {
	}

	public LiveData<Resource<ResultType>> asLiveData() {
		return result;
	}
}