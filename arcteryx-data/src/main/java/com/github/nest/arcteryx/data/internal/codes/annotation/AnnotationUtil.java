/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes.annotation;

import com.github.nest.arcteryx.data.IArcteryxDataExceptionCodes;
import com.github.nest.arcteryx.data.codes.CodesRuntimeException;
import com.github.nest.arcteryx.data.codes.ICodeTable;

/**
 * annotation utilities
 * 
 * @author brad.wu
 */
public class AnnotationUtil {
	/**
	 * get registration annotation
	 * 
	 * @param codeTableClass
	 * @return
	 */
	public static CodeTableRegistration getRegistration(Class<? extends ICodeTable> codeTableClass) {
		CodeTableRegistration registration = codeTableClass.getAnnotation(CodeTableRegistration.class);
		if (registration == null) {
			CodeTableReplacement replacement = codeTableClass.getAnnotation(CodeTableReplacement.class);
			if (replacement == null) {
				throw new CodesRuntimeException(IArcteryxDataExceptionCodes.CODE_TABLE_ANN_INVALID, "Neither ["
						+ CodeTableRegistration.class.getName() + "] nor [" + CodeTableReplacement.class.getName()
						+ "] annotation was defined in code table class.");
			} else {
				registration = getRegistration(replacement.replace());
			}
		}
		return registration;
	}
}
