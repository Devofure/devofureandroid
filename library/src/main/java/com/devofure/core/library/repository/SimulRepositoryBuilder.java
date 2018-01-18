package com.devofure.core.library.repository;


public interface SimulRepositoryBuilder {

	<T> SimulRepositoryBuilder add(T object, String path);

	<T> SimulRepositoryBuilder add(T object, String... pathParts);

	SimulRepositoryExecutor build();
}
