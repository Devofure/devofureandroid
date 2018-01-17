package com.devofure.core.library.repository;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

public abstract class FirebaseRepository extends BaseRepository {

	/**
	 * gives a default ParserTask
	 *
	 * @param tClass
	 * @param <T>
	 * @return
	 */
	@NonNull
	protected <T> ParserTask<T, DataSnapshot> geMappingTask(Class<T> tClass) {
		return rawData -> {
			if (rawData.exists()) {
				return rawData.getValue(tClass);
			}
			return null;
		};
	}
}
