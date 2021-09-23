package com.data.parser.processor;

import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

/**
 * Constant class
 * 
 * @author harikrishnan
 *
 */

@UtilityClass
public class Constants {

	public final Pattern MAIN_PATTERN = Pattern.compile("#(.*?)&");
	public final Pattern EACH_RECORD_PATTERN = Pattern.compile("\\((.*?)\\)\\)");
	public final Pattern FIRST_PIPE_PATTERN = Pattern.compile("([^\\|]*)\\|(.*)");
	public final Pattern FIRST_OPEN_BRACKET_PATTERN = Pattern.compile("([^\\(]*)\\((.*)");
	public final String VALUE = "Value";
	public final String REF = "Ref";
	public final String NAME = "Name";
	public final String HASH = "#";
	public final String OPEN_BRACKET = "(";
	public final String PIPE = "|";
	public final String CLOSE_BRACKET = ")";
	public final String AMPERSAND = "&";
	public final String COLON = ":";
	public final String DOUBLE_QUOTES = "\"";

}
