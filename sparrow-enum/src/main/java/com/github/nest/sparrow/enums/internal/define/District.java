/**
 * 
 */
package com.github.nest.sparrow.enums.internal.define;

import com.github.nest.sparrow.enums.define.ICity;
import com.github.nest.sparrow.enums.define.ICountry;
import com.github.nest.sparrow.enums.define.IDistrict;
import com.github.nest.sparrow.enums.define.IProvince;
import com.github.nest.sparrow.enums.internal.EnumItem;

/**
 * District
 * 
 * @author brad.wu
 */
public class District extends EnumItem implements IDistrict {
	private static final long serialVersionUID = -2627164049887914704L;
	private ICountry country = null;
	private IProvince province = null;
	private ICity city = null;

	public District(String id, String label, ICity city) {
		super(id, label);

		assert city == null : "City cannot be null for district [id=" + id + "].";

		this.city = city;
		this.province = city.getProvince();
		this.country = city.getCountry();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.IDistrict#getCity()
	 */
	@Override
	public ICity getCity() {
		return this.city;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.IDistrict#getProvince()
	 */
	@Override
	public IProvince getProvince() {
		return this.province;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.IDistrict#getCountry()
	 */
	@Override
	public ICountry getCountry() {
		return this.country;
	}

}
