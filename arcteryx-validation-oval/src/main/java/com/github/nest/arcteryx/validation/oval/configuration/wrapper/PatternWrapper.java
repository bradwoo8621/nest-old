/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.oval.constraint.MatchPatternCheck;
import net.sf.oval.constraint.NotMatchPatternCheck;

/**
 * wrapper of pattern
 * 
 * @author brad.wu
 */
public class PatternWrapper {
	private String pattern = null;
	private int flags = 0;

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the flags
	 */
	public int getFlags() {
		return flags;
	}

	/**
	 * @param flags
	 *            the flags to set
	 */
	public void setFlags(int flags) {
		this.flags = flags;
	}

	/**
	 * add to list
	 * 
	 * @param list
	 */
	public void addToCheck(MatchPatternCheck check) {
		Pattern[] patterns = check.getPatterns();
		List<Pattern> list = Arrays.asList(patterns);
		list = new ArrayList<Pattern>(list);
		Pattern pattern = Pattern.compile(getPattern(), getFlags());
		list.add(pattern);
		check.setPatterns(list);
	}

	/**
	 * add to list
	 * 
	 * @param list
	 */
	public void addToCheck(NotMatchPatternCheck check) {
		Pattern[] patterns = check.getPatterns();
		List<Pattern> list = Arrays.asList(patterns);
		list = new ArrayList<Pattern>(list);
		Pattern pattern = Pattern.compile(getPattern(), getFlags());
		list.add(pattern);
		check.setPatterns(list);
	}
}
