package com.devofure.core.library.ui;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;

import com.devofure.core.library.domain.Resource;

/**
 * base ViewModel that contains title and subtitle for using with data bindning
 * userKeyInput is to use with switchMap from {@link Transformations}
 */
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
