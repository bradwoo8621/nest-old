/**
 * 
 */
package com.github.nest.goose.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.github.nest.arcteryx.meta.ResourceException;

/**
 * file line cache provider
 * 
 * @author brad.wu
 */
public abstract class FileLineCacheProvider extends AbstractCodeBaseBeanCacheProvider {
	private List<Object> beanList = null;
	private String fileClassPath = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#getBeans()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Collection<T> getBeans() {
		return (Collection<T>) this.beanList;
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
	public void readFromClassPath() {
		try {
			List<Object> list = new LinkedList<Object>();
			InputStream is = getClass().getResourceAsStream(this.getFileClassPath());
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = reader.readLine()) != null) {
				list.add(createBean(line));
			}
			reader.close();

			this.beanList = new ArrayList<Object>(list.size());
			this.beanList.addAll(list);
		} catch (IOException e) {
			throw new ResourceException("Failed to read bean list by given class path [" + this.getFileClassPath()
					+ "].", e);
		}
	}

	/**
	 * create bean by given line
	 * 
	 * @param line
	 * @return
	 */
	protected abstract <T> T createBean(String line);
}
