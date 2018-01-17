package com.devofure.core.library.repository;

/**
 * defines a parser
 *
 * @param <REQUEST>  what it is now
 * @param <RESPONSE> what it will be
 */
public interface ParserTask<REQUEST, RESPONSE> {

	REQUEST parse(RESPONSE rawData);
}
