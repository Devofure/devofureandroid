package com.devofure.core.library.domain;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * The resource as Google recommends to use but with some modifications as a rawData and code
 *
 * @param <T>
 */
@SuppressWarnings("SimplifiableIfStatement")
public class Resource<T> {

	@NonNull
	public final Status status;

	@Nullable
	public final String message;

	public final int code;

	@Nullable
	public final T data;

	public final Object rawData;

	public Resource(@NonNull Status status, @Nullable T data, @Nullable Object rawData, @Nullable String message) {
		this(status, data, rawData, message, -1);
	}

	public Resource(@NonNull Status status, @Nullable T data, @Nullable Object rawData, @Nullable String message, int code) {
		this.status = status;
		this.data = data;
		this.message = message;
		this.code = code;
		this.rawData = rawData;
	}

	public static <T> Resource<T> success() {
		return success(null, null);
	}

	public static <T> Resource<T> success(@Nullable T data, Object rawData) {
		return new Resource<>(Status.SUCCESS, data, rawData, null);
	}

	public static <T> Resource<T> error(String msg, @Nullable T data, Object rawData) {
		return new Resource<>(Status.ERROR, data, rawData, msg);
	}

	public static <T> Resource<T> error(String msg, @Nullable T data, Object rawData, int code) {
		return new Resource<>(Status.ERROR, data, rawData, msg, code);
	}

	public static <T> Resource<T> loading() {
		return loading(null);
	}

	public static <T> Resource<T> loading(@Nullable T data) {
		return new Resource<>(Status.LOADING, data, null, null);
	}

	@Override
	public int hashCode() {
		int result = status.hashCode();
		result = 31 * result + (message != null ? message.hashCode() : 0);
		result = 31 * result + (data != null ? data.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Resource<?> resource = (Resource<?>) o;
		if (status != resource.status) {
			return false;
		}
		if (message != null ? !message.equals(resource.message) : resource.message != null) {
			return false;
		}
		return data != null ? data.equals(resource.data) : resource.data == null;
	}

	@Override
	public String toString() {
		return "Resource{" +
				"status=" + status +
				", message='" + message + '\'' +
				", data=" + (data != null ? data.toString() : "") +
				'}';
	}

	public boolean exists() {
		return data != null;
	}

	public boolean isSuccessful() {
		return status == Status.SUCCESS;
	}

	public boolean isError() {
		return status == Status.ERROR;
	}

	public boolean isLoading() {
		return status == Status.LOADING;
	}
}