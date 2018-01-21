package com.devofure.core.library.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by chavez on 2018-01-21.
 */
class PathBuilderTest {

	@Test
	void testHasBackslashAtTheEnd() {
		Assertions.assertTrue(PathUtils.hasBackslashAtTheEnd("/"));
		Assertions.assertFalse(PathUtils.hasBackslashAtTheEnd(""));
		Assertions.assertFalse(PathUtils.hasBackslashAtTheEnd("/jgyuygy"));
		Assertions.assertTrue(PathUtils.hasBackslashAtTheEnd("//"));
		Assertions.assertTrue(PathUtils.hasBackslashAtTheEnd("/khiguyhuyh/"));
		Assertions.assertTrue(PathUtils.hasBackslashAtTheEnd("gygvgh/"));
	}

	@Test
	void testHasBackslashAtTheStart() {
		Assertions.assertTrue(PathUtils.hasBackslashAtTheStart("/"));
		Assertions.assertFalse(PathUtils.hasBackslashAtTheStart(""));
		Assertions.assertTrue(PathUtils.hasBackslashAtTheStart("/jgyuygy"));
		Assertions.assertTrue(PathUtils.hasBackslashAtTheStart("//"));
		Assertions.assertTrue(PathUtils.hasBackslashAtTheStart("/khiguyhuyh/"));
		Assertions.assertFalse(PathUtils.hasBackslashAtTheStart("gygvgh/"));
	}
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme