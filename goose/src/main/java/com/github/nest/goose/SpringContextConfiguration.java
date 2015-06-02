/**
 * 
 */
package com.github.nest.goose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.goose.util.PathUtils;

/**
 * spring context configuration
 * 
 * @author brad.wu
 */
public class SpringContextConfiguration implements InitializingBean, ApplicationContextAware {
	private Map<String, SpringContext> contexts = new HashMap<String, SpringContext>();
	private ApplicationContext applicationContext;

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @return the contexts
	 */
	public List<SpringContext> getContexts() {
		List<SpringContext> list = new ArrayList<SpringContext>(this.contexts.size());
		list.addAll(this.contexts.values());
		return list;
	}

	/**
	 * @param contexts
	 *            the contexts to set
	 */
	public void setContexts(List<SpringContext> contexts) {
		if (contexts != null) {
			for (SpringContext context : contexts) {
				this.contexts.put(context.getId(), context);
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public synchronized void afterPropertiesSet() throws Exception {
		for (SpringContext context : this.contexts.values()) {
			load(context);
		}
	}

	/**
	 * load context
	 * 
	 * @param context
	 * @param loaded
	 */
	protected ApplicationContext load(SpringContext context) {
		ApplicationContext applicationContext = Context.getContext(context.getId());
		if (applicationContext != null) {
			// loaded
			return applicationContext;
		}

		if (StringUtils.isNotEmpty(context.getParentId())) {
			// load parent first
			ApplicationContext parent = this.load(this.contexts.get(context.getParentId()));
			// load myself
			return this.loadContext(context, parent);
		} else {
			// use root as parent
			return loadContext(context, this.getApplicationContext());
		}
	}

	/**
	 * load context
	 * 
	 * @param context
	 * @param parent
	 */
	protected ApplicationContext loadContext(SpringContext context, ApplicationContext parent) {
		if (PathUtils.isClasspath(context.getPath())) {
			// load from classpath
			return Context.createContextByClassPath(parent, context.getId(),
					PathUtils.getAbsoluteClasspath(context.getPath()));
		} else {
			// file system
			return Context.createContextByFileSystem(parent, context.getId(), context.getPath());
		}
	}
}
