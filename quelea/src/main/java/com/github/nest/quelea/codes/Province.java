/**
 * 
 */
package com.github.nest.quelea.codes;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import com.github.nest.quelea.codes.Province.ProvinceContentProvider;

/**
 * province
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = Province.CODE_TABLE_NAME)
@CodeTableProvider(contentProviderClass = ProvinceContentProvider.class)
public class Province extends AbstractCodeTable {
	private static final long serialVersionUID = -6324108595017704451L;
	public static final String CODE_TABLE_NAME = "Province";

	private Map<String, Province> provinceMap = new HashMap<String, Province>();

	/**
	 * get cities by given country and province code
	 * 
	 * @param countryCode
	 * @param provinceCode
	 * @return
	 */
	public City getCity(String countryCode, String provinceCode) {
		return ((City) this.getRegistry().getCodeTable(City.CODE_TABLE_NAME)).getCity(countryCode, provinceCode);
	}

	/**
	 * get districts by given country, province and city code
	 * 
	 * @param countryCode
	 * @param provinceCode
	 * @param cityCode
	 * @return
	 */
	public District getDistrict(String countryCode, String provinceCode, String cityCode) {
		return ((City) this.getRegistry().getCodeTable(City.CODE_TABLE_NAME)).getDistrict(countryCode, provinceCode,
				cityCode);
	}

	/**
	 * get province code table by give country code
	 * 
	 * @param countryCode
	 * @return
	 */
	public Province getProvince(final String countryCode) {
		Province province = this.getProvinceFromCache(countryCode);
		if (province == null) {
			synchronized (this.provinceMap) {
				province = this.getProvinceFromCache(countryCode);
				if (province == null) {
					final Collection<ICodeItem> provinceItems = this.getItems(new ICodeItemFilter() {
						/**
						 * (non-Javadoc)
						 * 
						 * @see com.github.nest.arcteryx.data.codes.ICodeItemFilter#accept(com.github.nest.arcteryx.data.codes.ICodeItem)
						 */
						@Override
						public boolean accept(ICodeItem item) {
							ProvinceCodeItem provinceItem = (ProvinceCodeItem) item;
							return provinceItem.getCountryCode().equals(countryCode);
						}
					});
					// the code table will not be registered to registry, so
					// will get null when call getRegistry() method.
					province = new Province() {
						private static final long serialVersionUID = 1L;

						/**
						 * (non-Javadoc)
						 * 
						 * @see com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable#initializeName()
						 */
						@Override
						protected void initializeName() {
							this.setName(Province.CODE_TABLE_NAME);
						}

						/**
						 * (non-Javadoc)
						 * 
						 * @see com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable#createContentProvider()
						 */
						@Override
						protected ICodeTableContentProvider createContentProvider() throws CodesRuntimeException {
							return new ProvinceContentProvider() {
								/**
								 * (non-Javadoc)
								 * 
								 * @see com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider#initializeItems(com.github.nest.arcteryx.data.codes.ICodeTable)
								 */
								@Override
								protected Set<ICodeItem> initializeItems(ICodeTable codeTable) {
									Set<ICodeItem> set = new HashSet<ICodeItem>();
									set.addAll(provinceItems);
									return set;
								}
							};
						}
					};
					putProvinceToCache(countryCode, province);
				}
			}
		}
		return province;
	}

	/**
	 * put province into cache
	 * 
	 * @param countryCode
	 * @param province
	 */
	protected void putProvinceToCache(final String countryCode, Province province) {
		this.provinceMap.put(countryCode, province);
	}

	/**
	 * get province from cache by given country code
	 * 
	 * @param countryCode
	 * @return
	 */
	protected Province getProvinceFromCache(String countryCode) {
		return this.provinceMap.get(countryCode);
	}

	/**
	 * province code item
	 * 
	 * @author brad.wu
	 *
	 */
	public static class ProvinceCodeItem extends CodeItem {
		private static final long serialVersionUID = 6129187274960547579L;
		private String countryCode = null;

		public ProvinceCodeItem(String code, String countryCode) {
			super(code);
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
	}

	/**
	 * province content provider.<br>
	 * only read the first and second parts of line, separated by "|".
	 * 
	 * @author brad.wu
	 */
	public static class ProvinceContentProvider extends DefaultCodeTableContentProvider {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider#createCodeItem(java.lang.String)
		 */
		@Override
		protected ICodeItem createCodeItem(String line) {
			String[] ss = line.split("[|]");
			return new ProvinceCodeItem(ss[0], ss[1]);
		}
	}
}
