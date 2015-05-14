/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes.annotation;

import com.github.nest.arcteryx.data.IArcteryxDataExceptionCodes;
import com.github.nest.arcteryx.data.codes.CodesRuntimeException;
import com.github.nest.arcteryx.data.codes.ICodeTable;
import com.github.nest.arcteryx.data.codes.ICodeTableContentProvider;

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
	public static String getRegistrationName(Class<? extends ICodeTable> codeTableClass) {
		CodeTableRegistration registration = codeTableClass.getAnnotation(CodeTableRegistration.class);
		if (registration == null) {
			CodeTableReplacement replacement = codeTableClass.getAnnotation(CodeTableReplacement.class);
			if (replacement == null) {
				throw new CodesRuntimeException(IArcteryxDataExceptionCodes.CODE_TABLE_ANN_INVALID, "Neither ["
						+ CodeTableRegistration.class.getName() + "] nor [" + CodeTableReplacement.class.getName()
						+ "] annotation was defined in code table class.");
			} else {
				return getRegistrationName(replacement.replace());
			}
		}
		return registration.name();
	}

	/**
	 * create content provider by annotation {@linkplain CodeTableProvider}, if
	 * no annotated, return null.
	 * 
	 * @param codeTableClass
	 * @return
	 */
	public static ICodeTableContentProvider createContentProvider(Class<? extends ICodeTable> codeTableClass) {
		CodeTableProvider annotation = codeTableClass.getAnnotation(CodeTableProvider.class);
		if (annotation == null) {
			return null;
		}
		// else
		try {
			return annotation.contentProviderClass().newInstance();
		} catch (Exception e) {
			throw new CodesRuntimeException(IArcteryxDataExceptionCodes.CODE_TABLE_CONTENT_PROVIDER_CONSTRUCT,
					"Failed to construct code table provider[" + annotation.contentProviderClass() + "].", e);
		}
	}
}
