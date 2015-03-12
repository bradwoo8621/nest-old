/**
 * 
 */
package com.github.nest.sparrow.party.internal.operator;

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
import com.github.nest.sparrow.party.internal.PartyRoleType;

/**
 * country cache provider
 * 
 * @author brad.wu
 */
public class PartyRoleTypeCacheProvider extends AbstractCodeBaseBeanCacheProvider {
	private List<PartyRoleType> partyRoleTypeList = null;
	private String fileClassPath = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanCacheProvider#getBeans()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> Collection<T> getBeans() {
		return (Collection<T>) this.partyRoleTypeList;
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
	 * set party role type list class path location
	 * 
	 * @param classpath
	 * @throws IOException
	 */
	public void readFromClassPath() {
		try {
			List<PartyRoleType> list = new LinkedList<PartyRoleType>();
			InputStream is = getClass().getResourceAsStream(this.getFileClassPath());
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] ss = line.split("[|]");
				PartyRoleType roleType = new PartyRoleType();
				roleType.setCode(ss[0]);
				roleType.setName(ss[1]);
				list.add(roleType);
			}
			reader.close();

			this.partyRoleTypeList = new ArrayList<PartyRoleType>(list.size());
			this.partyRoleTypeList.addAll(list);
		} catch (IOException e) {
			throw new ResourceException("Failed to read party role type list by given class path ["
					+ this.getFileClassPath() + "].", e);
		}
	}
}
