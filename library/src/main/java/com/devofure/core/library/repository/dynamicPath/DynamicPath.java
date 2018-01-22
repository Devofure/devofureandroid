package com.devofure.core.library.repository.dynamicPath;

import static com.devofure.core.library.util.PathUtils.createPath;

/**
 * a dynamic generator path in that order: RootPath, MainPath, AfterMainPath, UserKey, AfterMainPath);
 */
public class DynamicPath implements DynamicPathFromSecondPart {

	private String mRootPath;
	private String mFirstPathPart;
	private String mSecondPathPart;
	private String mThirdPathPart;
	private String mFourthPathPart;

	public DynamicPath(String rootPath) {
		this.mRootPath = rootPath;
	}

	public DynamicPath setFirstPathPart(String... firstPathPart) {
		mFirstPathPart = createPath(firstPathPart);
		return this;
	}

	public DynamicPath setFirstPathPart(String mainPath) {
		mFirstPathPart = mainPath;
		return this;
	}

	@Override
	public DynamicPath setSecondPathPart(String... secondPathPart) {
		this.mSecondPathPart = createPath(secondPathPart);
		return this;
	}

	@Override
	public DynamicPath setSecondPathPart(String secondPathPart) {
		this.mSecondPathPart = secondPathPart;
		return this;
	}

	@Override
	public DynamicPath setThirdPathPart(String... thirdPathPart) {
		this.mThirdPathPart = createPath(thirdPathPart);
		return this;
	}

	@Override
	public DynamicPath setThirdPathPart(String thirdPathPart) {
		this.mThirdPathPart = thirdPathPart;
		return this;
	}

	@Override
	public DynamicPath setFourthPathPart(String... mFourthPathPart) {
		this.mFourthPathPart = createPath(mFourthPathPart);
		return this;
	}

	@Override
	public DynamicPath setFourthPathPart(String mFourthPathPart) {
		this.mFourthPathPart = mFourthPathPart;
		return this;
	}

	@Override
	public String generate() {
		return createPath(mRootPath, mFirstPathPart, mSecondPathPart, mThirdPathPart, mFourthPathPart);
	}
}
