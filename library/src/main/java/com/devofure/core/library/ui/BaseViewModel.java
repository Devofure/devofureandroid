package com.devofure.core.library.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.devofure.core.library.domain.Resource;

public abstract class BaseViewModel extends AndroidViewModel {

	public final ObservableField<String> title = new ObservableField<>();
	public final ObservableField<String> subTitle = new ObservableField<>();
	public final MutableLiveData<String> userKeyInput = new MutableLiveData<>();

	public BaseViewModel(@NonNull Application application) {
		super(application);
	}

	public <T> LiveData<T> removeResourceShell(LiveData<Resource<T>> resourceLiveData) {
		return Transformations.map(resourceLiveData, resource -> resource.data);
	}

}
