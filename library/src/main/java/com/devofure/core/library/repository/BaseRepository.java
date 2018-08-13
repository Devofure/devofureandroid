package com.devofure.core.library.repository;

import androidx.lifecycle.LiveData;

import com.devofure.core.library.domain.Resource;

import java.util.Map;

public class BaseRepository {

	/**
	 * returns the liveData by key and stores new liveData instances if there are no existing
	 *
	 * @param bank             Map where all liveData of a specific request type are stored
	 * @param key the key to get or store the liveData
	 * @param liveDataSupplier gives a liveData if there are no liveData with that key.
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
