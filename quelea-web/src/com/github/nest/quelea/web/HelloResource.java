/**
 * 
 */
package com.github.nest.quelea.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.restlet.resource.ServerResource;

import com.github.nest.goose.restlet.jaxrs.JaxRSResource;

/**
 * hello resource
 * 
 * @author brad.wu
 */
@JaxRSResource(module = "quelea")
@Path("/hello")
public class HelloResource extends ServerResource {
	/**
	 * @return
	 */
	@GET
	@Produces("text/plain")
	@Path("/world")
	public String hello() {
		return "Hello, world!";
	}
}
