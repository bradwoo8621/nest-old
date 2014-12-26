package com.github.nest.arcteryx.validation.hibernate.custom;

import java.util.ArrayList;
import java.util.List;

public class TestObject {
	private String string = null;
	private List<InnerObject> inners = new ArrayList<InnerObject>();

	public TestObject(String string) {
		this.string = string;
		this.inners.add(new InnerObject("c"));
		this.inners.add(new InnerObject("d"));
	}

	/**
	 * @return the string
	 */
	public String getString() {
		return string;
	}

	/**
	 * @param string
	 *            the string to set
	 */
	public void setString(String string) {
		this.string = string;
	}

	/**
	 * @return the inners
	 */
	public List<InnerObject> getInners() {
		return inners;
	}

	/**
	 * @param inners
	 *            the inners to set
	 */
	public void setInners(List<InnerObject> inners) {
		this.inners = inners;
	}
}