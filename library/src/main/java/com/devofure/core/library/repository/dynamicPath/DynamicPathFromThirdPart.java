package com.devofure.core.library.repository.dynamicPath;

public interface DynamicPathFromThirdPart extends DynamicPathFromFourthPart {

	DynamicPath setThirdPathPart(String... thirdPathPart);

	DynamicPath setThirdPathPart(String thirdPathPart);

}
