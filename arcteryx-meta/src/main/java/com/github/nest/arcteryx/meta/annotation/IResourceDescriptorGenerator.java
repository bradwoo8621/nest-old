package com.github.nest.arcteryx.meta.annotation;

import com.github.nest.arcteryx.meta.IResourceDescriptor;

/**
 * bean descriptor generator
 * 
 * @author brad.wu
 *
 */
public interface IResourceDescriptorGenerator {
	/**
	 * set aware
	 * 
	 * @param aware
	 */
	void setAware(ArcteryxResourceAware aware);

	/**
	 * get aware
	 * 
	 * @return
	 */
	ArcteryxResourceAware getAware();

	/**
	 * create descriptor
	 * 
	 * @param resourceClass
	 * 
	 * @return
	 * @throws Exception
	 */
	IResourceDescriptor createDescriptor(Class<?> resourceClass) throws Exception;
}