/**
 * 
 */
package com.github.nest.sparrow.enums.internal.define;

import java.util.ArrayList;
import java.util.List;

import com.github.nest.sparrow.enums.define.ICountry;
import com.github.nest.sparrow.enums.define.IProvince;
import com.github.nest.sparrow.enums.internal.CodedEnumItem;

/**
 * Country
 * 
 * @author brad.wu
 */
public class Country extends CodedEnumItem implements ICountry {
	private static final long serialVersionUID = -1153415855766281310L;
	private List<IProvince> provinces = null;
	private String abbreviation = null;

	public Country(String id, String label, String code, String abbreviation) {
		super(id, label, code);
		this.abbreviation = abbreviation;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.ICountry#getProvinces()
	 */
	@Override
	public List<IProvince> getProvinces() {
		return this.provinces;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.ICountry#addProvince(com.github.nest.sparrow.enums.define.IProvince)
	 */
	@Override
	public void addProvince(IProvince province) {
		if (this.provinces == null) {
			synchronized (this) {
				if (this.provinces == null) {
					this.provinces = new ArrayList<IProvince>();
				}
			}
		}
		if (!this.provinces.contains(province)) {
			synchronized (this) {
				if (!this.provinces.contains(province)) {
					this.provinces.add(province);
				}
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.ICountry#getAbbreviation()
	 */
	@Override
	public String getAbbreviation() {
		return this.abbreviation;
	}
}
