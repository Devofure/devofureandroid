package com.devofure.core.library.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.annotation.NonNull;

/**
 * merge different models into one key-value store model, @see {@link MediatorModel}
 * in every change of any of the liveData merged will the Mediator supplier notify the observer
 */
public class MediatorSupplier {
	private MediatorLiveData<Resource<MediatorModel>> liveDataMerger = null;

	public MediatorSupplier() {
		liveDataMerger = new MediatorLiveData<>();
		liveDataMerger.setValue(Resource.loading(new MediatorModel()));
	}

	/**
	 * add need liveData to be merged into on key-value store model
	 *
	 * @param liveDataResource the liveData to add
	 * @param <T>              the type of the model in the Resource that the liveData contains
	 * @return the same instance to use as a builder/chain
	 */
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

	/**
	 * gives a LiveData to listen to
	 * @return
	 */
	public LiveData<Resource<MediatorModel>> asLiveData() {
		return liveDataMerger;
	}
}
