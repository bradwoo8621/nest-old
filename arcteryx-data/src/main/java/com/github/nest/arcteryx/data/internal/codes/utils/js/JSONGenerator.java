/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes.utils.js;

import com.github.nest.arcteryx.data.codes.ICodeItem;
import com.github.nest.arcteryx.data.codes.ICodeTable;
import com.github.nest.arcteryx.data.codes.ICodeTableRegistry;

/**
 * JSON generator
 * 
 * @author brad.wu
 */
public class JSONGenerator {
	/**
	 * generate a code table to json object
	 * 
	 * @param codeTable
	 * @return
	 */
	public static String generate(ICodeTable codeTable) {
		StringBuilder sb = new StringBuilder("var Codes = {").append(generateCodeTable(codeTable)).append("};");
		return sb.toString();
	}

	protected static String generateCodeTable(ICodeTable codeTable) {
		StringBuilder sb = new StringBuilder();
		sb.append(codeTable.getName()).append(":new Codes([");
		for (ICodeItem item : codeTable.getItems()) {
			sb.append("{id:\"").append(item.getCode()).append("\", text:\"\"},");
		}
		if (sb.charAt(sb.length() - 1) == ',') {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("])");
		return sb.toString();
	}

	/**
	 * generate code tables of given registry
	 * 
	 * @param registry
	 * @return
	 */
	public static String generate(ICodeTableRegistry registry) {
		StringBuilder sb = new StringBuilder("var Codes = {");
		for (ICodeTable codeTable : registry.all()) {
			sb.append(generateCodeTable(codeTable)).append(",");
		}
		if (sb.charAt(sb.length() - 1) == ',') {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("};");
		return sb.toString();
	}
}
