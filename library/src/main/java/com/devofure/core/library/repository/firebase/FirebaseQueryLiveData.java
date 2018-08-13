package com.devofure.core.library.repository.firebase;

import androidx.lifecycle.LiveData;
import android.os.Handler;
import androidx.annotation.NonNull;

import com.devofure.core.library.domain.Resource;
import com.devofure.core.library.repository.ParserTask;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * https://firebase.googleblog.com/2017/12/using-android-architecture-components.html
 * https://firebase.googleblog.com/2017/12/using-android-architecture-components_20.html
 * https://firebase.googleblog.com/2017/12/using-android-architecture-components_22.html
 */
public class FirebaseQueryLiveData<REQUEST> extends LiveData<Resource<REQUEST>> {

	private final Query query;
	private final ValueEventListener listener;
	private final Handler handler = new Handler();
	private boolean listenerRemovePending = false;
	private final Runnable removeListener = new Runnable() {
		@Override
		public void run() {
			query.removeEventListener(listener);
			listenerRemovePending = false;
		}
	};

	public FirebaseQueryLiveData(@NonNull Query query, ParserTask<REQUEST, DataSnapshot> parserTask) {
		this.query = query;
		this.listener = new ResourceEventListener(parserTask);
	}

	public FirebaseQueryLiveData(DatabaseReference ref, ParserTask<REQUEST, DataSnapshot> parserTask) {
		this.query = ref;
		this.listener = new ResourceEventListener(parserTask);
	}

	@Override
	protected void onActive() {
		if (listenerRemovePending) {
			handler.removeCallbacks(removeListener);
		} else {
			query.addValueEventListener(listener);
		}
		listenerRemovePending = false;
	}

	@Override
	protected void onInactive() {
		// Listener removal is schedule on a two second delay
		handler.postDelayed(removeListener, 2000);
		listenerRemovePending = true;
	}

	/*	@Override
		protected void onActive() {
			query.addValueEventListener(listener);
		}

		@Override
		protected void onInactive() {
			query.removeEventListener(listener);
		}
	*/
	public class ResourceEventListener implements ValueEventListener {
		private ParserTask<REQUEST, DataSnapshot> mParserTask;

		public ResourceEventListener(ParserTask<REQUEST, DataSnapshot> parserTask) {
			mParserTask = parserTask;
		}

		@Override
		public void onDataChange(DataSnapshot dataSnapshot) {
			setValue(Resource.success(mParserTask.parse(dataSnapshot), dataSnapshot));
		}

		@Override
		public void onCancelled(DatabaseError databaseError) {
			setValue(Resource.error(databaseError.getMessage(), null, null, databaseError.getCode()));
		}
	}
	/*public class ResourceOnlySignInEventListener implements ValueEventListener {

		public ResourceOnlySignInEventListener() {}

		@Override
		public void onDataChange(DataSnapshot dataSnapshot) {
			setValue(Resource.success(dataSnapshot));
		}

		@Override
		public void onCancelled(DatabaseError databaseError) {
			setValue(Resource.error(databaseError.getMessage(), null, databaseError.getCode()));
		}
	}*/
}