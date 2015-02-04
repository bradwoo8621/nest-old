/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.github.nest.arcteryx.meta.beans.internal.AbstractStaticCodeBeanOperator;
import com.github.nest.arcteryx.persistent.IPersistentBeanSaver;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;

/**
 * hibernate persistent saver
 * 
 * @author brad.wu
 */
public class HibernatePersistentSaver extends AbstractStaticCodeBeanOperator implements IPersistentBeanSaver {
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentBeanSaver#insert(java.lang.Object)
	 */
	@Override
	public void insert(Object resource) {
		getCurrentSession().save(resource);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentBeanSaver#update(java.lang.Object)
	 */
	@Override
	public void update(Object resource) {
		this.getCurrentSession().update(resource);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.persistent.IPersistentBeanSaver#save(java.lang.Object)
	 */
	@Override
	public void save(Object resource) {
		this.getCurrentSession().saveOrUpdate(resource);
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
