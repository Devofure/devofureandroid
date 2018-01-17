package com.devofure.core.library.repository;

public interface ParserTask<REQUEST, RESPONSE> {

	REQUEST parse(RESPONSE rawData);
}
