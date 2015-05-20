/**
 * 
 */
package com.github.nest.quelea.codes;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.github.nest.arcteryx.data.codes.CodesRuntimeException;
import com.github.nest.arcteryx.data.codes.ICodeItem;
import com.github.nest.arcteryx.data.codes.ICodeItemFilter;
import com.github.nest.arcteryx.data.codes.ICodeTable;
import com.github.nest.arcteryx.data.codes.ICodeTableContentProvider;
import com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable;
import com.github.nest.arcteryx.data.internal.codes.CodeItem;
import com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableProvider;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;
import com.github.nest.quelea.codes.City.CityContentProvider;

/**
 * city
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = City.CODE_TABLE_NAME)
@CodeTableProvider(contentProviderClass = CityContentProvider.class)
public class City extends AbstractCodeTable {
	private static final long serialVersionUID = 4974189141383900609L;
	public final static String CODE_TABLE_NAME = "City";
	private Map<String, City> cityMap = new HashMap<String, City>();

	/**
	 * get district by given city code
	 * 
	 * @param cityCode
	 * @return
	 */
	public District getDistrict(String countryCode, String provinceCode, String cityCode) {
		return ((District) this.getRegistry().getCodeTable(District.CODE_TABLE_NAME)).getDistrict(countryCode,
				provinceCode, cityCode);
	}

	/**
	 * get city by given province code
	 * 
	 * @param countryCode
	 * @param provinceCode
	 * @return
	 */
	public City getCity(final String countryCode, final String provinceCode) {
		City city = this.getCityFromCache(countryCode, provinceCode);
		if (city == null) {
			synchronized (this.cityMap) {
				city = this.getCityFromCache(countryCode, provinceCode);
				if (city == null) {
					final Collection<ICodeItem> cityItems = this.getItems(new ICodeItemFilter() {
						/**
						 * (non-Javadoc)
						 * 
						 * @see com.github.nest.arcteryx.data.codes.ICodeItemFilter#accept(com.github.nest.arcteryx.data.codes.ICodeItem)
						 */
						@Override
						public boolean accept(ICodeItem item) {
							CityCodeItem cityItem = (CityCodeItem) item;
							return cityItem.getCountryCode().equals(countryCode)
									&& cityItem.getProvinceCode().equals(provinceCode);
						}
					});
					// the code table will not be registered to registry, so
					// will get null when call getRegistry() method.
					city = new City() {
						private static final long serialVersionUID = 1L;

						/**
						 * (non-Javadoc)
						 * 
						 * @see com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable#initializeName()
						 */
						@Override
						protected void initializeName() {
							this.setName(City.CODE_TABLE_NAME);
						}

						/**
						 * (non-Javadoc)
						 * 
						 * @see com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable#createContentProvider()
						 */
						@Override
						protected ICodeTableContentProvider createContentProvider() throws CodesRuntimeException {
							return new CityContentProvider() {
								/**
								 * (non-Javadoc)
								 * 
								 * @see com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider#initializeItems(com.github.nest.arcteryx.data.codes.ICodeTable)
								 */
								@Override
								protected Set<ICodeItem> initializeItems(ICodeTable codeTable) {
									Set<ICodeItem> set = new HashSet<ICodeItem>();
									set.addAll(cityItems);
									return set;
								}
							};
						}
					};
					this.putCityToCache(countryCode, provinceCode, city);
				}
			}
		}
		return city;
	}

	/**
	 * put city into cache
	 * 
	 * @param countryCode
	 * @param provinceCode
	 * @param city
	 */
	protected void putCityToCache(String countryCode, String provinceCode, City city) {
		this.cityMap.put(generateKey(countryCode, provinceCode), city);
	}

	/**
	 * generate map key
	 * 
	 * @param countryCode
	 * @param provinceCode
	 * @return
	 */
	protected String generateKey(String countryCode, String provinceCode) {
		return StringUtils.join(new String[] { countryCode, provinceCode }, '|');
	}

	/**
	 * get city from cache by given country code
	 * 
	 * @param countryCode
	 * @param provinceCode
	 * @return
	 */
	protected City getCityFromCache(String countryCode, String provinceCode) {
		return this.cityMap.get(generateKey(countryCode, provinceCode));
	}

	/**
	 * city code item
	 * 
	 * @author brad.wu
	 *
	 */
	public static class CityCodeItem extends CodeItem {
		private static final long serialVersionUID = 6129187274960547579L;
		private String provinceCode = null;
		private String countryCode = null;

		public CityCodeItem(String code, String provinceCode, String countryCode) {
			super(code);
			this.setProvinceCode(provinceCode);
			this.setCountryCode(countryCode);
		}

		/**
		 * @return the countryCode
		 */
		public String getCountryCode() {
			return countryCode;
		}

		/**
		 * @param countryCode
		 *            the countryCode to set
		 */
		protected void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		/**
		 * @return the provinceCode
		 */
		protected String getProvinceCode() {
			return provinceCode;
		}

		/**
		 * @param provinceCode
		 *            the provinceCode to set
		 */
		protected void setProvinceCode(String provinceCode) {
			this.provinceCode = provinceCode;
		}
	}

	/**
	 * city content provider.<br>
	 * only read the first, second and third parts of line, separated by "|".
	 * 
	 * @author brad.wu
	 */
	public static class CityContentProvider extends DefaultCodeTableContentProvider {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider#createCodeItem(java.lang.String)
		 */
		@Override
		protected ICodeItem createCodeItem(String line) {
			String[] ss = line.split("[|]");
			return new CityCodeItem(ss[0], ss[1], ss[2]);
		}
	}
}
