package com.devofure.core.library.repository.firebase;

import com.devofure.core.library.repository.SimulRepositoryBuilder;
import com.devofure.core.library.repository.SimulRepositoryExecutor;
import com.devofure.core.library.util.PathUtils;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;


/**
 * helper class that takens in data to be same at the same time in firebase, this firebase update is done all at once
 */
public class FireSimulRepositoryBuilder implements SimulRepositoryBuilder {

	private DatabaseReference mRootDbReference;
	private Map<String, Object> mChildUpdates;

	public FireSimulRepositoryBuilder(DatabaseReference rootDbReference) {
		mRootDbReference = rootDbReference;
		mChildUpdates = new HashMap<>();
	}

	@Override
	public <T> SimulRepositoryBuilder add(T object, String path) {
		mChildUpdates.put(path, object);
		return this;
	}

	@Override
	public <T> SimulRepositoryBuilder add(T object, String... pathParts) {
		add(object, PathUtils.createPath(pathParts));
		return this;
	}

	@Override
	public SimulRepositoryExecutor build() {
		return () -> mRootDbReference.updateChildren(mChildUpdates);
	}
}
