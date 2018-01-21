package com.devofure.core.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PathGeneratorTest {

	@Test
	void testSetFirstPathPart() {
		String path = new PathGenerator("rootPath")
				.setFirstPathPart("mainPathParts")
				.generate();
		Assertions.assertEquals("/rootPath/mainPathParts", path);
	}

	@Test
	void testSetSecondPathPart() {
		String path = new PathGenerator("rootPath")
				.setFirstPathPart("mainPathParts")
				.setSecondPathPart("keyUserKey")
				.generate();
		Assertions.assertEquals("/rootPath/mainPathParts/keyUserKey", path);
	}

	@Test
	void testSetFirstPathPartWithMultipleValues() {
		String path = new PathGenerator("rootPath")
				.setFirstPathPart("mainPathParts", "multi")
				.generate();
		Assertions.assertEquals("/rootPath/mainPathParts/multi", path);
	}

	@Test
	void testSetSecondPathPartWithMultipleValues() {
		String path = new PathGenerator("rootPath")
				.setFirstPathPart("mainPathParts")
				.setSecondPathPart("keyUserKey", "multi")
				.generate();
		Assertions.assertEquals("/rootPath/mainPathParts/keyUserKey/multi", path);
	}

	@Test
	void testSetFirstAndSecondPathPartWithMultipleValues() {
		String path = new PathGenerator("rootPath")
				.setFirstPathPart("mainPathParts", "multi1")
				.setSecondPathPart("keyUserKey", "multi")
				.generate();
		Assertions.assertEquals("/rootPath/mainPathParts/multi1/keyUserKey/multi", path);
	}

	@Test
	void testSetBackSlashAlwaysCorrect() {
		String path = new PathGenerator("rootPath/")
				.setFirstPathPart("/mainPathParts/")
				.setSecondPathPart("/keyUserKey")
				.generate();
		Assertions.assertEquals("/rootPath/mainPathParts/keyUserKey", path);
	}
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme