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
import com.github.nest.quelea.codes.District.DistrictContentProvider;

/**
 * district
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = District.CODE_TABLE_NAME)
@CodeTableProvider(contentProviderClass = DistrictContentProvider.class)
public class District extends AbstractCodeTable {
	private static final long serialVersionUID = 1977487115389952407L;
	public final static String CODE_TABLE_NAME = "District";
	private Map<String, District> cityMap = new HashMap<String, District>();

	/**
	 * get district by given country, province and city code
	 * 
	 * @param countryCode
	 * @param provinceCode
	 * @param cityCode
	 * @return
	 */
	public District getDistrict(final String countryCode, final String provinceCode, final String cityCode) {
		District district = this.getDistrictFromCache(countryCode, provinceCode, cityCode);
		if (district == null) {
			synchronized (this.cityMap) {
				district = this.getDistrictFromCache(countryCode, provinceCode, cityCode);
				if (district == null) {
					final Collection<ICodeItem> cityItems = this.getItems(new ICodeItemFilter() {
						/**
						 * (non-Javadoc)
						 * 
						 * @see com.github.nest.arcteryx.data.codes.ICodeItemFilter#accept(com.github.nest.arcteryx.data.codes.ICodeItem)
						 */
						@Override
						public boolean accept(ICodeItem item) {
							DistrictCodeItem districtItem = (DistrictCodeItem) item;
							return districtItem.getCountryCode().equals(countryCode)
									&& districtItem.getProvinceCode().equals(provinceCode)
									&& districtItem.getCityCode().equals(cityCode);
						}
					});
					// the code table will not be registered to registry, so
					// will get null when call getRegistry() method.
					district = new District() {
						private static final long serialVersionUID = 1L;

						/**
						 * (non-Javadoc)
						 * 
						 * @see com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable#initializeName()
						 */
						@Override
						protected void initializeName() {
							this.setName(District.CODE_TABLE_NAME);
						}

						/**
						 * (non-Javadoc)
						 * 
						 * @see com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable#createContentProvider()
						 */
						@Override
						protected ICodeTableContentProvider createContentProvider() throws CodesRuntimeException {
							return new DistrictContentProvider() {
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
					this.putDistrictToCache(countryCode, provinceCode, cityCode, district);
				}
			}
		}
		return district;
	}

	/**
	 * put district into cache
	 * 
	 * @param countryCode
	 * @param provinceCode
	 * @param cityCode
	 * @param district
	 */
	protected void putDistrictToCache(String countryCode, String provinceCode, String cityCode, District district) {
		this.cityMap.put(generateKey(countryCode, provinceCode, cityCode), district);
	}

	/**
	 * generate map key
	 * 
	 * @param countryCode
	 * @param provinceCode
	 * @param cityCode
	 * @return
	 */
	protected String generateKey(String countryCode, String provinceCode, String cityCode) {
		return StringUtils.join(new String[] { countryCode, provinceCode, cityCode }, '|');
	}

	/**
	 * get district from cache by given country, province and city code
	 * 
	 * @param countryCode
	 * @return
	 */
	protected District getDistrictFromCache(String countryCode, String provinceCode, String cityCode) {
		return this.cityMap.get(generateKey(countryCode, provinceCode, cityCode));
	}

	/**
	 * city code item
	 * 
	 * @author brad.wu
	 *
	 */
	public static class DistrictCodeItem extends CodeItem {
		private static final long serialVersionUID = 6129187274960547579L;
		private String cityCode = null;
		private String provinceCode = null;
		private String countryCode = null;

		public DistrictCodeItem(String code, String cityCode, String provinceCode, String countryCode) {
			super(code);
			this.setCityCode(cityCode);
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

		/**
		 * @return the cityCode
		 */
		protected String getCityCode() {
			return cityCode;
		}

		/**
		 * @param cityCode
		 *            the cityCode to set
		 */
		protected void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
	}

	/**
	 * province content provider.<br>
	 * only read the first, second and third parts of line, separated by "|".
	 * 
	 * @author brad.wu
	 */
	public static class DistrictContentProvider extends DefaultCodeTableContentProvider {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider#createCodeItem(java.lang.String)
		 */
		@Override
		protected ICodeItem createCodeItem(String line) {
			String[] ss = line.split("[|]");
			return new DistrictCodeItem(ss[0], ss[1], ss[2], ss[3]);
		}
	}
}
