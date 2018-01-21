package com.devofure.core.library.repository;

import static com.devofure.core.library.util.PathUtils.createPath;

/**
 * a dynamic generator path in that order: RootPath, MainPath, AfterMainPath, UserKey, AfterMainPath);
 */
public class PathGenerator {

	private String mRootPath;
	private String mFirstPathPart;
	private String mSecondPathPart;
	private String mThirdPathPart;

	public PathGenerator(String rootPath) {
		this.mRootPath = rootPath;
	}

	public PathGenerator setFirstPathPart(String... firstPathPart) {
		mFirstPathPart = createPath(firstPathPart);
		return this;
	}

	public PathGenerator setFirstPathPart(String mainPath) {
		mFirstPathPart = mainPath;
		return this;
	}

	public PathGenerator setSecondPathPart(String... secondPathPart) {
		this.mSecondPathPart = createPath(secondPathPart);
		return this;
	}

	public PathGenerator setSecondPathPart(String secondPathPart) {
		this.mSecondPathPart = secondPathPart;
		return this;
	}

	public PathGenerator setThirdPathPart(String... thirdPathPart) {
		this.mThirdPathPart = createPath(thirdPathPart);
		return this;
	}

	public PathGenerator setThirdPathPart(String thirthPathPart) {
		this.mThirdPathPart = thirthPathPart;
		return this;
	}

	public String generate() {
		return createPath(mRootPath, mFirstPathPart, mSecondPathPart, mThirdPathPart);
	}

}
