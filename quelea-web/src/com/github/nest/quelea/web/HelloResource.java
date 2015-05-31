/**
 * 
 */
package com.github.nest.quelea.web;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * hello resource
 * 
 * @author brad.wu
 */
public class HelloResource extends ServerResource {
	/**
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.resource.ServerResource#get()
	 */
	@Override
	protected Representation get() throws ResourceException {
		return new StringRepresentation("Hello, world!", MediaType.TEXT_PLAIN);
	}
}
