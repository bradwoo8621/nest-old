/**
 * 
 */
package com.github.nest.arcteryx.persistent.oneToMany;

import java.util.List;
import java.util.Set;

/**
 * @author brad.wu
 *
 */
public class Person {
	private Long id = null;
	private String name = null;
	private List<Address> addresses = null;
	private Set<Address> addressSet = null;

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
	 * @return the addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	/**
	 * @return the addressSet
	 */
	public Set<Address> getAddressSet() {
		return addressSet;
	}

	/**
	 * @param addressSet
	 *            the addressSet to set
	 */
	public void setAddressSet(Set<Address> addressSet) {
		this.addressSet = addressSet;
	}
}
