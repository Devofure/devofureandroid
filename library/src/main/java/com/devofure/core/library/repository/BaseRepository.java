package com.devofure.core.library.repository;

import android.arch.lifecycle.LiveData;

import com.devofure.core.library.domain.Resource;

import java.util.Map;

public class BaseRepository {

	/**
	 * stores the liveData instance if there are no stored instance and returns the liveData by key
	 *
	 * @param bank             Map where all liveData of a specific request type are stored
	 * @param key
	 * @param liveDataSupplier
	 * @param <T>
	 * @return
	 */
	public <T> LiveData<Resource<T>> retrieveLiveData(Map<String, LiveData<Resource<T>>> bank, String key, Supplier<LiveData<Resource<T>>> liveDataSupplier) {
		LiveData<Resource<T>> existingData = bank.get(key);
		if (existingData == null) {
			LiveData<Resource<T>> newLiveData = liveDataSupplier.get();
			bank.put(key, newLiveData);
			return newLiveData;
		}
		return existingData;
	}

	public interface Supplier<T> {

		/**
		 * Gets a result.
		 *
		 * @return a result
		 */
		T get();
	}
}
