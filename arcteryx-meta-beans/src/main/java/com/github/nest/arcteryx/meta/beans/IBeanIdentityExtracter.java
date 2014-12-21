/**
 * 
 */
package com.github.nest.arcteryx.meta.beans;

/**
 * bean identity extracter
 * 
 * @author brad.wu
 */
public interface IBeanIdentityExtracter {
	/**
	 * extract bean identity
	 * 
	 * @param bean
	 * @return
	 */
	IBeanIdentity extract(Object bean);
}
