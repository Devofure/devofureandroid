package com.devofure.core.library.domain;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * represents a model with joined models from the repository
 */
public class MediatorModel {

	private Map<String, Resource<?>> classMap = new HashMap<>();

	public <T> void set(Resource<T> newResource) {
		if (newResource != null && newResource.exists()) {
			classMap.put(newResource.data.getClass().getSimpleName(), newResource);
		}
	}

	public <T> Resource<T> get(@NonNull Class<T> classType) {
		return (Resource<T>) classMap.get(classType.getSimpleName());
	}

	/**
	 * all stored Resources have to be successful to show as successful
	 *
	 * @return
	 */
	public boolean isSuccessful() {
		for (Resource<?> resource : classMap.values()) {
			if (resource.isSuccessful()) {
				//only check if successful for performance
				continue;
			}
			return false;
		}
		return true;
	}

	/**
	 * If One resource is loading then all is loading
	 *
	 * @return
	 */
	public boolean isLoading() {
		for (Resource<?> resource : classMap.values()) {
			if (resource.isLoading()) {
				//only check if successful for performance
				return true;
			}
		}
		return false;
	}

	/**
	 * If One resource is error then show as error
	 *
	 * @return
	 */
	public boolean isError() {
		for (Resource<?> resource : classMap.values()) {
			if (resource.isLoading()) {
				//only check if successful for performance
				return true;
			}
		}
		return false;
	}

	public Resource<?> getFirstError() {
		for (Resource<?> resource : classMap.values()) {
			if (resource.isError()) {
				//only check if successful for performance
				return resource;
			}
		}
		return null;
	}
}
