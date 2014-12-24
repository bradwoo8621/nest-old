/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.configuration.wrapper;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.constraint.InstanceOfAnyCheck;
import net.sf.oval.constraint.InstanceOfCheck;

/**
 * wrapper of class types
 * 
 * @author brad.wu
 */
public class ClassTypesWrapper {
	private List<String> types = new ArrayList<String>();

	/**
	 * add class type
	 * 
	 * @param type
	 */
	public void add(String type) {
		this.types.add(type);
	}

	protected Class<?>[] convertToArray() throws ClassNotFoundException {
		Class<?>[] array = new Class<?>[this.types.size()];
		for (int index = 0, count = array.length; index < count; index++) {
				array[index] = Class.forName(this.types.get(index));
		}
		return array;
	}

	/**
	 * set to check
	 * 
	 * @param check
	 * @throws ClassNotFoundException 
	 */
	public void setToCheck(InstanceOfCheck check) throws ClassNotFoundException {
		check.setTypes(this.convertToArray());
	}

	/**
	 * set to check
	 * 
	 * @param check
	 * @throws ClassNotFoundException 
	 */
	public void setToCheck(InstanceOfAnyCheck check) throws ClassNotFoundException {
		check.setTypes(this.convertToArray());
	}
}
