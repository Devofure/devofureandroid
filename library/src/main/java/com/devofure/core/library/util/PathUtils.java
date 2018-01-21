package com.devofure.core.library.util;

public class PathUtils {

	public static boolean hasBackslashAtTheStart(StringBuilder stringBuilder) {
		return stringBuilder.indexOf("/") == 0;
	}

	public static boolean hasBackslashAtTheEnd(String path) {
		return path.length() >= 1 && path.endsWith("/");
	}

	public static String createPath(String... pathParts) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String path : pathParts) {
			if (path == null || path.isEmpty()) {
				continue;
			}
			if (hasBackslashAtTheEnd(stringBuilder)
					&& hasBackslashAtTheStart(path)) {
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			} else if (!hasBackslashAtTheEnd(stringBuilder)
					&& !hasBackslashAtTheStart(path)) {
				stringBuilder.append("/");
			}
			stringBuilder.append(path);
		}
		return stringBuilder.toString();
	}

	public static boolean hasBackslashAtTheEnd(StringBuilder stringBuilder) {
		return stringBuilder.length() >= 1 && stringBuilder.lastIndexOf("/") == stringBuilder.length() - 1;
	}

	public static boolean hasBackslashAtTheStart(String path) {
		return path.indexOf("/") == 0;
	}
}
