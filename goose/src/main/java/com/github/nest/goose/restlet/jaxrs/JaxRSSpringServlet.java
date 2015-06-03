/**
 * 
 */
package com.github.nest.goose.restlet.jaxrs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.engine.adapter.HttpServerHelper;
import org.restlet.engine.adapter.ServerCall;
import org.springframework.context.ApplicationContext;

/**
 * spring based servlet
 * 
 * @author brad.wu
 * @author yi.feng
 */
public class JaxRSSpringServlet extends HttpServlet {
	private static final long serialVersionUID = -4833708753876623002L;

	/**
	 * spring context id
	 * 
	 * @see Context
	 */
	public static final String SPRING_CONTEXT_ID_ATTRIBUTE = "spring.context.id";
	/**
	 * The Servlet context initialization parameter's name containing the name
	 * of the Servlet context attribute that should be used to store the HTTP
	 * server connector instance.
	 */
	private static final String NAME_SERVER_ATTRIBUTE = "org.restlet.attribute.server";

	/** The default value for the NAME_SERVER_ATTRIBUTE parameter. */
	private static final String NAME_SERVER_ATTRIBUTE_DEFAULT = "com.github.nest.goose.SpringServlet.server";

	private String springContextId = null;

	private volatile transient HttpServerHelper helper;

	/**
	 * get spring application context
	 * 
	 * @return
	 */
	protected ApplicationContext getApplicationContext() {
		return com.github.nest.arcteryx.context.Context.getContext(springContextId);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.ext.servlet.ServerServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();

		springContextId = this.getInitParameter(SPRING_CONTEXT_ID_ATTRIBUTE);
		if (StringUtils.isBlank(springContextId)) {
			throw new ServletException("Value of parameter [" + SPRING_CONTEXT_ID_ATTRIBUTE + "] cannot be blank.");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServerHelper helper = getServer(request);

		if (helper != null) {
			helper.handle(createCall(helper.getHelped(), request, response));
		} else {
			log("[Restlet] Unable to get the Restlet HTTP server connector. Status code 500 returned.");
			response.sendError(500);
		}
	}

	/**
	 * Creates a new Servlet call wrapping a Servlet request/response couple and
	 * a Server connector.
	 * 
	 * @param server
	 *            The Server connector.
	 * @param request
	 *            The Servlet request.
	 * @param response
	 *            The Servlet response.
	 * @return The new ServletCall instance.
	 */
	protected ServerCall createCall(Server server, HttpServletRequest request, HttpServletResponse response) {
		return new ServletCall(server, request, response);
	}

	/**
	 * Returns the associated HTTP server handling calls. It creates a new one
	 * if none exists.
	 * 
	 * @param request
	 *            The HTTP Servlet request.
	 * @return The HTTP server handling calls.
	 */
	public HttpServerHelper getServer(HttpServletRequest request) {
		// Lazy initialization with double-check.
		HttpServerHelper result = this.helper;

		if (result == null) {
			synchronized (this) {
				result = this.helper;
				if (result == null) {
					// Find the attribute name to use to store the server
					// reference
					final String serverAttributeName = getInitParameter(NAME_SERVER_ATTRIBUTE,
							NAME_SERVER_ATTRIBUTE_DEFAULT + "." + getServletName());

					// Look up the attribute for a target
					result = (HttpServerHelper) getServletContext().getAttribute(serverAttributeName);

					if (result == null) {
						result = createServer(request);
						getServletContext().setAttribute(serverAttributeName, result);
					}

					this.helper = result;
				}
			}
		}

		return result;
	}

	/**
	 * Creates the associated HTTP server handling calls.
	 * 
	 * @param request
	 *            The HTTP Servlet request.
	 * @return The new HTTP server handling calls.
	 */
	protected HttpServerHelper createServer(HttpServletRequest request) {
		HttpServerHelper result = null;
		Component component = getComponent(request);

		if (component != null) {
			// First, let's create a pseudo server
			Server server = new Server(component.getContext().createChildContext(), (List<Protocol>) null,
					this.getLocalAddr(request), this.getLocalPort(request), component);
			result = new HttpServerHelper(server);

			// Change the default adapter
			Context serverContext = server.getContext();
			serverContext.getParameters().add("adapter", "org.restlet.ext.servlet.internal.ServletServerAdapter");
		}

		return result;
	}

	/**
	 * get component
	 * 
	 * @return
	 */
	protected Component getComponent(HttpServletRequest request) {
		ServletComponent component = this.getApplicationContext().getBean(ServletComponent.class);
		component.setUriPatternPrefix(this.getContextPath(request) + request.getServletPath());
		component.initApplications();
		return component;
	}

	/**
	 * Intercepter method need for subclasses such as XdbServerServlet.
	 * 
	 * @param request
	 *            The Servlet request.
	 * @return The Internet Protocol (IP) address of the interface on which the
	 *         request was received.
	 */
	protected String getLocalAddr(HttpServletRequest request) {
		return request.getLocalAddr();
	}

	/**
	 * Intercepter method need for subclasses such as XdbServerServlet.
	 * 
	 * @param request
	 *            The Servlet request.
	 * @return The Internet Protocol (IP) port number of the interface on which
	 *         the request was received
	 */
	protected int getLocalPort(HttpServletRequest request) {
		return request.getLocalPort();
	}

	/**
	 * Intercepter method need for subclasses such as XdbServerServlet.
	 * 
	 * @param request
	 *            The Servlet request.
	 * @return The portion of the request URI that indicates the context of the
	 *         request.
	 */
	protected String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	/**
	 * Returns the value of a given initialization parameter, first from the
	 * Servlet configuration, then from the Web Application context.
	 * 
	 * @param name
	 *            The parameter name.
	 * @param defaultValue
	 *            The default to use in case the parameter is not found.
	 * @return The value of the parameter or null.
	 */
	public String getInitParameter(String name, String defaultValue) {
		String result = getServletConfig().getInitParameter(name);

		if (result == null) {
			result = getServletConfig().getServletContext().getInitParameter(name);
		}

		if (result == null) {
			result = defaultValue;
		}

		return result;
	}
}
