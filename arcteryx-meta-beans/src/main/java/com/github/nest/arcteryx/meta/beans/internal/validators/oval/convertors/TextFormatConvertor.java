/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.oval.Check;
import net.sf.oval.constraint.MatchPatternCheck;

import com.github.nest.arcteryx.meta.beans.constraints.TextFormatConstraint;

/**
 * text format convertor
 * 
 * @author brad.wu
 */
public class TextFormatConvertor extends AbstractOValPropertyCheckConvertor<TextFormatConstraint> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.IOValCheckConvertor#getSupportedConstraintType()
	 */
	@Override
	public Class<TextFormatConstraint> getSupportedConstraintType() {
		return TextFormatConstraint.class;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.oval.convertors.AbstractOValCheckConvertor#createCheck(com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint)
	 */
	@Override
	protected Check createCheck(TextFormatConstraint constraint) {
		MatchPatternCheck check = new MatchPatternCheck();
		String[] patterns = constraint.getPatterns();
		List<Pattern> compiledPatterns = new LinkedList<Pattern>();
		if (patterns != null) {
			for (String pattern : patterns) {
				compiledPatterns.add(Pattern.compile(pattern));
			}
		}
		check.setPatterns(compiledPatterns);
		check.setMatchAll(constraint.isMatchAll());
		return check;
	}
}
