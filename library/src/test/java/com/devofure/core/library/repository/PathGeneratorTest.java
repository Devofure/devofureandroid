package com.devofure.core.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PathGeneratorTest {
	PathGenerator pathGenerator = new PathGenerator("rootPath");

	@Test
	void testSetMainPath() {
		String path = new PathGenerator("rootPath")
				.setMainPath("mainPathParts")
				.generate();
		Assertions.assertEquals("/rootPath/mainPathParts", path);
	}

	@Test
	void testSetUserKey() {
		String path = new PathGenerator("rootPath")
				.setMainPath("mainPathParts")
				.setUserKey("keyUserKey")
				.generate();
		Assertions.assertEquals("/rootPath/mainPathParts/keyUserKey", path);
	}

	@Test
	void testSetBackSlashAlwaysCorrect() {
		String path = new PathGenerator("rootPath/")
				.setMainPath("/mainPathParts/")
				.setUserKey("/keyUserKey")
				.generate();
		Assertions.assertEquals("/rootPath/mainPathParts/keyUserKey", path);
	}
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme