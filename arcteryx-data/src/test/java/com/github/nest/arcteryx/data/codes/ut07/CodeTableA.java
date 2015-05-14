/**
 * 
 */
package com.github.nest.arcteryx.data.codes.ut07;

import com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable;
import com.github.nest.arcteryx.data.internal.codes.HierarchyCodeTableContentProvider;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableProvider;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;

/**
 * @author brad.wu
 */
@CodeTableRegistration(registerClass = CodeTableA.class, name = "CodeTableA")
@CodeTableProvider(contentProviderClass = HierarchyCodeTableContentProvider.class)
public class CodeTableA extends AbstractCodeTable {
	private static final long serialVersionUID = -4676077845092953939L;
}
