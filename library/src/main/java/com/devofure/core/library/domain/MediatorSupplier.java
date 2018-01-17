package com.devofure.core.library.domain;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

public class MediatorSupplier {
	private MediatorLiveData<Resource<MediatorModel>> liveDataMerger = null;

	public MediatorSupplier() {
		liveDataMerger = new MediatorLiveData<>();
		liveDataMerger.setValue(Resource.loading(new MediatorModel()));
	}

	public <T> MediatorSupplier merge(LiveData<Resource<T>> liveDataResource) {
		liveDataMerger.addSource(liveDataResource, value -> liveDataMerger.setValue(mergeValue(value)));
		return this;
	}

	private <T> Resource<MediatorModel> mergeValue(Resource<T> newResource) {
		MediatorModel mediatorModel = liveDataMerger.getValue().data;
		mediatorModel.set(newResource);
		return getMainResource(mediatorModel);
	}

	@NonNull
	private Resource<MediatorModel> getMainResource(MediatorModel mediatorModel) {
		if (mediatorModel.isSuccessful()) {
			return Resource.success(mediatorModel, mediatorModel);
		} else if (mediatorModel.isLoading()) {
			return Resource.loading(mediatorModel);
		} else { //if(mediatorModel.isError()){
			return Resource.error(mediatorModel.getFirstError().message, null, null);
		}

	}

	public LiveData<Resource<MediatorModel>> asLiveData() {
		return liveDataMerger;
	}
}
