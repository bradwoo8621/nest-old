/**
 * 
 */
package com.github.nest.sparrow.enums.internal.define;

import java.util.ArrayList;
import java.util.List;

import com.github.nest.sparrow.enums.define.ICity;
import com.github.nest.sparrow.enums.define.ICountry;
import com.github.nest.sparrow.enums.define.IDistrict;
import com.github.nest.sparrow.enums.define.IProvince;
import com.github.nest.sparrow.enums.internal.CodedEnumItem;

/**
 * City
 * 
 * @author brad.wu
 */
public class City extends CodedEnumItem implements ICity {
	private static final long serialVersionUID = 8880226799227902714L;
	private ICountry country = null;
	private IProvince province = null;
	private List<IDistrict> districts = null;
	private String abbreviation = null;

	public City(String id, String label, String code, IProvince province, String abbreviation) {
		super(id, label, code);

		assert province == null : "Province cannot be null for city [id=" + id + ", code=" + code + "].";

		this.province = province;
		this.province.addCity(this);
		this.country = this.province.getCountry();
		this.abbreviation = abbreviation;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.ICity#getProvince()
	 */
	@Override
	public IProvince getProvince() {
		return this.province;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.ICity#getCountry()
	 */
	@Override
	public ICountry getCountry() {
		return this.country;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.ICity#getDistricts()
	 */
	@Override
	public List<IDistrict> getDistricts() {
		return this.districts;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.ICity#addDistrict(com.github.nest.sparrow.enums.define.IDistrict)
	 */
	@Override
	public void addDistrict(IDistrict district) {
		if (this.districts == null) {
			synchronized (this) {
				if (this.districts == null) {
					this.districts = new ArrayList<IDistrict>();
				}
			}
		}
		if (!this.districts.contains(district)) {
			synchronized (this) {
				if (!this.districts.contains(district)) {
					this.districts.add(district);
				}
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.ICity#getAbbreviation()
	 */
	@Override
	public String getAbbreviation() {
		return this.abbreviation;
	}
}
