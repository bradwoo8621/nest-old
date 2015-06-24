/**
 * 
 */
package com.github.nest.goose.restlet.jaxrs;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.restlet.ext.jaxrs.JaxRsRestlet;

/**
 * {@code Throwable} mapper for jax-rs exception.<br>
 * convert {@code Throwable} stack trace to string format and build
 * {@code Response}.
 * 
 * @author brad.wu
 * @see JaxRsRestlet#handleInvocationTargetExc
 */
@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {
	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		pw.close();
		return Response.status(ResponseStatus.SERVER_EXCEPTION_RAISED).type(MediaType.TEXT_PLAIN_TYPE)
				.entity(sw.toString()).build();
	}
}
