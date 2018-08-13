package com.devofure.core.library.domain;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * notifies only one observer
 *
 * @param <T>
 */
public class SingleLiveEvent<T> extends MutableLiveData<T> {

	private static final String TAG = "SingleLiveEvent";

	private final AtomicBoolean mPending = new AtomicBoolean(false);

	@Override
	public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
		super.observe(owner, observer);
		if (hasActiveObservers()) {
			//Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
		}

		// Observe the internal MutableLiveData
		super.observe(owner, data -> {
			if (mPending.compareAndSet(true, false)) {
				observer.onChanged(data);
			}
		});
	}

	/**
	 * Used for cases where T is Void, to make calls cleaner.
	 */
	@MainThread
	public void call() {
		setValue(null);
	}

	@MainThread
	public void setValue(@Nullable T t) {
		mPending.set(true);
		super.setValue(t);
	}
}