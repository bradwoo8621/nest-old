/**
 * 
 */
package com.github.nest.goose.internal.location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.github.nest.arcteryx.meta.ResourceException;
import com.github.nest.goose.internal.AbstractCodeBaseBeanCacheProvider;

/**
 * country cache provider
 * 
 * @author brad.wu
 */
public class CountryCacheProvider extends AbstractCodeBaseBeanCacheProvider {
	private List<Country> countryList = null;
	private String fileClassPath = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#getBeans()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Collection<T> getBeans() {
		return (Collection<T>) this.countryList;
	}

	/**
	 * @return the fileClassPath
	 */
	public String getFileClassPath() {
		return fileClassPath;
	}

	/**
	 * @param fileClassPath
	 *            the fileClassPath to set
	 */
	public void setFileClassPath(String fileClassPath) {
		this.fileClassPath = fileClassPath;
	}

	/**
	 * set country list class path location
	 * 
	 * @param classpath
	 * @throws IOException
	 */
	public void readCountryListFromClassPath() {
		try {
			List<Country> list = new LinkedList<Country>();
			InputStream is = getClass().getResourceAsStream(this.getFileClassPath());
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] ss = line.split("[|]");
				Country country = new Country();
				country.setAbbr2(ss[0]);
				country.setAbbr3(ss[1]);
				country.setInternationalDialingCode(ss[2]);
				country.setName(ss[3]);
				list.add(country);
			}
			reader.close();

			this.countryList = new ArrayList<Country>(list.size());
			this.countryList.addAll(list);
		} catch (IOException e) {
			throw new ResourceException("Failed to read country list by given class path [" + this.getFileClassPath() + "].", e);
		}
	}
}
