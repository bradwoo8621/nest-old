/**
 * 
 */
package com.github.nest.arcteryx.data.codes.ut05;

import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableReplacement;

/**
 * B must extends A
 * 
 * @author brad.wu
 */
@CodeTableReplacement(replace = CodeTableA.class)
public class CodeTableB extends CodeTableA {
	private static final long serialVersionUID = -7982162474518441294L;
}
