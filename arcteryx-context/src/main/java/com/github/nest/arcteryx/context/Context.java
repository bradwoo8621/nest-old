/**
 * 
 */
package com.github.nest.arcteryx.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Context. Should be loaded when initializing. Each application context should
 * have its own unique identity.<br>
 * Context will keep the application context instance in memory, and provide an
 * API {{@link #getContext(String)} to retrieve the context in memory.
 * 
 * @author brad.wu
 */
public class Context {
	private static Map<String, ApplicationContext> contextMap = new HashMap<String, ApplicationContext>();

	/**
	 * get application context by given id
	 * 
	 * @param id
	 * @return
	 */
	public static ApplicationContext getContext(String id) {
		return contextMap.get(id);
	}

	/**
	 * create application context by given class paths
	 * 
	 * @param id
	 * @param configLocation
	 * 
	 * @return
	 */
	public static synchronized ApplicationContext createContextByClassPath(String id,
			String... configLocation) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		if (id != null) {
			context.setId(id);
		}
		contextMap.put(context.getId(), context);
		return context;
	}

	/**
	 * create application context by given class paths and parent context
	 * 
	 * @param id
	 * @param parent
	 * @param configLocation
	 * 
	 * @return
	 */
	public static synchronized ApplicationContext createContextByClassPath(ApplicationContext parent,
			String id, String... configLocation) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation, parent);
		if (id != null) {
			context.setId(id);
		}
		contextMap.put(context.getId(), context);
		return context;
	}

	/**
	 * create application context by give file system paths
	 * 
	 * @param id
	 * @param configLocation
	 * 
	 * @return
	 */
	public static synchronized ApplicationContext createContextByFileSystem(String id, String... configLocation) {
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(configLocation);
		if (id != null) {
			context.setId(id);
		}
		contextMap.put(context.getId(), context);
		return context;
	}

	/**
	 * create application context by given file system paths and parent context
	 * 
	 * @param id
	 * @param parent
	 * @param configLocation
	 * 
	 * @return
	 */
	public static synchronized ApplicationContext createContextByFileSystem(ApplicationContext parent, String id,
			String... configLocation) {
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(configLocation, parent);
		if (id != null) {
			context.setId(id);
		}
		contextMap.put(context.getId(), context);
		return context;
	}
}
