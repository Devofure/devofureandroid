package com.devofure.core.library.repository.firebase;

import android.support.annotation.NonNull;

import com.devofure.core.library.repository.BaseRepository;
import com.devofure.core.library.repository.ParserTask;
import com.devofure.core.library.repository.model.DbModel;
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

	/**
	 * gives a default ParserTask with the key as internal key
	 *
	 * @param tClass
	 * @param <T>
	 * @return
	 */
	@NonNull
	protected <T extends DbModel> ParserTask<T, DataSnapshot> geMappingWithKeyTask(Class<T> tClass) {
		return rawData -> {
			if (rawData.exists()) {
				T t = rawData.getValue(tClass);
				if (t != null) {
					t.setKey(rawData.getKey());
				}
			}
			return null;
		};
	}
}
