package com.devofure.core.library.repository.repoTask;

import android.database.sqlite.SQLiteDatabase;

public class RepositoryTaskExecutorBuilder<T extends DaoExecutable> {

	private final T mDao;
	private boolean mIsTransaction;

	public RepositoryTaskExecutorBuilder(T dao) {
		mDao = dao;
	}

	public RepositoryTaskExecutorBuilder setTransaction(boolean enable) {
		this.mIsTransaction = enable;
		return this;
	}

	public void executeTask(RepositoryTask<T> task) {
		SQLiteDatabase db = mDao.getDb();
		try {
			if (mIsTransaction) {
				db.beginTransaction();
			}
			task.execute(mDao);
			if (mIsTransaction) {
				db.setTransactionSuccessful();
				db.endTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}
}
