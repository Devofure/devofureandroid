package com.devofure.core.library.repository.repoTask;

public interface RepositoryTask<T extends DaoExecutable> {
	void execute(T dao);
}
