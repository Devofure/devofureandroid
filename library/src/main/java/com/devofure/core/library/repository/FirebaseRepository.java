package com.devofure.core.library.repository;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by chavez on 2018-01-09.
 */

public abstract class FirebaseRepository extends BaseRepository {

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
