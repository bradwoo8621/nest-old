/**
 * 
 */
package com.github.nest.arcteryx.persistent.manyToMany;

import java.util.List;
import java.util.Set;

/**
 * @author brad.wu
 *
 */
public class Student {
	private Long id = null;
	private String name = null;
	private List<Teacher> teacheres = null;
	private Set<Teacher> teacherSet = null;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the teacheres
	 */
	public List<Teacher> getTeacheres() {
		return teacheres;
	}

	/**
	 * @param teacheres
	 *            the teacheres to set
	 */
	public void setTeacheres(List<Teacher> teacheres) {
		this.teacheres = teacheres;
	}

	/**
	 * @return the teacherSet
	 */
	public Set<Teacher> getTeacherSet() {
		return teacherSet;
	}

	/**
	 * @param teacherSet
	 *            the teacherSet to set
	 */
	public void setTeacherSet(Set<Teacher> teacherSet) {
		this.teacherSet = teacherSet;
	}
}
