/**
 * 
 */
package com.github.nest.quelea.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.resource.ServerResource;

import com.github.nest.goose.restlet.jaxrs.JaxRSResource;

/**
 * party query resource
 * 
 * @author brad.wu
 */
@JaxRSResource(module = "quelea")
@Path("/party")
public class PartyResource extends ServerResource {
	/**
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/query")
	public PartyQueryCriteria query(PartyQueryCriteria criteria) throws Exception {
		System.out.println("partyTypeCode: " + criteria);
		criteria.setIdNumber("1234567890");
		throw new Exception("World destroyed.");
	}
}
