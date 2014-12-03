/**
 * 
 */
package com.github.nest.sparrow.enums.internal.define;

import java.util.ArrayList;
import java.util.List;

import com.github.nest.sparrow.enums.define.ICity;
import com.github.nest.sparrow.enums.define.ICountry;
import com.github.nest.sparrow.enums.define.IProvince;
import com.github.nest.sparrow.enums.internal.CodedEnumItem;

/**
 * Province
 * 
 * @author brad.wu
 */
public class Province extends CodedEnumItem implements IProvince {
	private static final long serialVersionUID = 8962356037528326615L;
	private ICountry country = null;
	private List<ICity> cities = null;
	private String abbreviation = null;

	public Province(String id, String label, String code, ICountry country, String abbreviation) {
		super(id, label, code);

		assert country != null : "Country cannot be null for province [id=" + id + ", code=" + code + "].";

		this.country = country;
		this.country.addProvince(this);
		this.abbreviation = abbreviation;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.IProvince#getCountry()
	 */
	@Override
	public ICountry getCountry() {
		return this.country;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.IProvince#getCities()
	 */
	@Override
	public List<ICity> getCities() {
		return this.cities;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.IProvince#addCity(com.github.nest.sparrow.enums.define.ICity)
	 */
	@Override
	public void addCity(ICity city) {
		if (this.cities == null) {
			synchronized (this) {
				if (this.cities == null) {
					this.cities = new ArrayList<ICity>();
				}
			}
		}
		if (!this.cities.contains(city)) {
			synchronized (this) {
				if (!this.cities.contains(city)) {
					this.cities.add(city);
				}
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.sparrow.enums.define.IProvince#getAbbreviation()
	 */
	@Override
	public String getAbbreviation() {
		return this.abbreviation;
	}
}
