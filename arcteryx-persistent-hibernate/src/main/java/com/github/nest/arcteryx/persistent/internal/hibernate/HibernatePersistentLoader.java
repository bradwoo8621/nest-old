/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.github.nest.arcteryx.meta.beans.internal.AbstractStaticCodeBeanOperator;
import com.github.nest.arcteryx.persistent.IPersistentBeanLoader;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;

/**
 * hibernate persistent loader
 * 
 * @author brad.wu
 */
public class HibernatePersistentLoader extends AbstractStaticCodeBeanOperator implements IPersistentBeanLoader {

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentBeanLoader#load(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T load(Serializable id) {
		return (T) this.getCurrentSession().load(this.getBeanDescriptor().getBeanClass(), id);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentBeanLoader#load(java.lang.Object,
	 *      java.io.Serializable)
	 */
	@Override
	public <T> T load(T object, Serializable id) {
		this.getCurrentSession().load(object, id);
		return object;
	}

	/**
	 * get current hibernate session
	 * 
	 * @return
	 */
	protected Session getCurrentSession() {
		IPersistentConfiguration configuration = this.getBeanDescriptor().getContext()
				.getInitializedData(IPersistentConfigurationInitializer.KEY);
		SessionFactory sessionFactory = configuration.getRealConfiguration();
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.internal.AbstractStaticCodeResourceOperator#createCode()
	 */
	@Override
	protected String createCode() {
		return CODE;
	}

}
