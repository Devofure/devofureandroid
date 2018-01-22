package com.devofure.core.library.repository.dynamicPath;


public interface DynamicPathFromSecondPart extends DynamicPathFromThirdPart {

	DynamicPath setSecondPathPart(String... secondPathPart);

	DynamicPath setSecondPathPart(String secondPathPart);
}
