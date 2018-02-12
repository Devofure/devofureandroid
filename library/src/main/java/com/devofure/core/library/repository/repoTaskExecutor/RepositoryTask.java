package com.devofure.core.library.repository.repoTaskExecutor;

public interface RepositoryTask<T extends DaoExecutable> {
	void execute(T dao);
}
