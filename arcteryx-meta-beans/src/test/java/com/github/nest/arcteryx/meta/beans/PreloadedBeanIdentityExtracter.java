/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

/**
 * @author brad.wu
 *
 */
public class PreloadedBeanIdentityExtracter implements IBeanIdentityExtracter {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.IBeanIdentityExtracter#extract(java.lang.Object)
	 */
	@Override
	public IBeanIdentity extract(Object bean) {
		assert bean instanceof PreloadedBean;

		BeanID id = new BeanID();
		id.setId(((PreloadedBean) bean).getId());
		return id;
	}
}
