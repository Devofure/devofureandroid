package com.devofure.core.library.repository;

import static com.devofure.core.library.util.PathUtils.createPath;

/**
 * a dynamic generator path : RootPath, MainPath, BeforeUserKey, mUserKey, mAfterUserKey);
 */
public class PathGenerator {

	private String mRootPath;
	private String mUserKey;
	private String mMainPath;
	private String mAfterUserKey;
	private String mBeforeUserKey;

	public PathGenerator(String rootPath) {
		this.mRootPath = rootPath;
	}

	public PathGenerator setMainPath(String... mainPathParts) {
		mMainPath = createPath(mainPathParts);
		return this;
	}

	public PathGenerator setMainPath(String mainPath) {
		mMainPath = mainPath;
		return this;
	}

	public PathGenerator setUserKey(String userKey) {
		mUserKey = userKey;
		return this;
	}

	public PathGenerator setAfterUserKey(String... afterUserKey) {
		this.mAfterUserKey = createPath(afterUserKey);
		return this;
	}

	public PathGenerator setBeforeUserKey(String... beforeUserKey) {
		this.mBeforeUserKey = createPath(beforeUserKey);
		return this;
	}

	public String generate() {
		return createPath(mRootPath, mMainPath, mBeforeUserKey, mUserKey, mAfterUserKey);
	}

}
