/**
 * 
 */
package com.github.nest.quelea.codes;

import com.github.nest.arcteryx.data.codes.ICodeItem;
import com.github.nest.arcteryx.data.internal.codes.AbstractCodeTable;
import com.github.nest.arcteryx.data.internal.codes.CodeItem;
import com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableProvider;
import com.github.nest.arcteryx.data.internal.codes.annotation.CodeTableRegistration;
import com.github.nest.quelea.codes.Country.CountryContentProvider;

/**
 * country
 * 
 * @author brad.wu
 */
@CodeTableRegistration(name = Country.CODE_TABLE_NAME)
@CodeTableProvider(contentProviderClass = CountryContentProvider.class)
public class Country extends AbstractCodeTable {
	private static final long serialVersionUID = 1799017142460502603L;
	public static final String CODE_TABLE_NAME = "Country";

	/**
	 * get province by given country code
	 * 
	 * @param countryCode
	 * @return
	 */
	public Province getProvince(String countryCode) {
		return ((Province) this.getRegistry().getCodeTable(Province.CODE_TABLE_NAME)).getProvince(countryCode);
	}

	/**
	 * get city by given country code and province code
	 * 
	 * @param countryCode
	 * @param provinceCode
	 * @return
	 */
	public City getCity(String countryCode, String provinceCode) {
		return ((Province) this.getRegistry().getCodeTable(Province.CODE_TABLE_NAME))
				.getCity(countryCode, provinceCode);
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
		return ((Province) this.getRegistry().getCodeTable(Province.CODE_TABLE_NAME)).getDistrict(countryCode,
				provinceCode, cityCode);
	}

	/**
	 * country code item
	 * 
	 * @author brad.wu
	 *
	 */
	public static class CountryCodeItem extends CodeItem {
		private static final long serialVersionUID = -1459565306591553540L;

		private String teleAreaCode = null;

		public CountryCodeItem(String code, String teleAreaCode) {
			super(code);
			this.setTeleAreaCode(teleAreaCode);
		}

		/**
		 * @return the teleAreaCode
		 */
		public String getTeleAreaCode() {
			return teleAreaCode;
		}

		/**
		 * @param teleAreaCode
		 *            the teleAreaCode to set
		 */
		protected void setTeleAreaCode(String teleAreaCode) {
			this.teleAreaCode = teleAreaCode;
		}
	}

	/**
	 * country content provider.<br>
	 * only read the first and second parts of line, separated by "|".
	 * 
	 * @author brad.wu
	 */
	public static class CountryContentProvider extends DefaultCodeTableContentProvider {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.data.internal.codes.DefaultCodeTableContentProvider#createCodeItem(java.lang.String)
		 */
		@Override
		protected ICodeItem createCodeItem(String line) {
			String[] ss = line.split("[|]");
			return new CountryCodeItem(ss[0], ss[1]);
		}
	}
}
