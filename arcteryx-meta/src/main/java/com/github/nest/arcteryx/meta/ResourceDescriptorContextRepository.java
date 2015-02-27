/**
 * 
 */
package com.github.nest.arcteryx.meta;

import java.util.HashMap;
import java.util.Map;

/**
 * resource descriptor context repository
 * 
 * @author brad.wu
 */
public class ResourceDescriptorContextRepository {
	private static Map<String, IResourceDescriptorContext> contextMap = new HashMap<String, IResourceDescriptorContext>();

	/**
	 * register context
	 * 
	 * @param context
	 */
	public static void register(IResourceDescriptorContext context) {
		String name = context.getName();
		contextMap.put(name, context);
	}

	/**
	 * unregister context
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends IResourceDescriptorContext> T unregister(String name) {
		return (T) contextMap.remove(name);
	}

	/**
	 * get context by given name
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends IResourceDescriptorContext> T getContext(String name) {
		return (T) contextMap.get(name);
	}
}
