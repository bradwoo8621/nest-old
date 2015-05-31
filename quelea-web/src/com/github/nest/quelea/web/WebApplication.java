/**
 * 
 */
package com.github.nest.quelea.web;

import org.restlet.Application;
import org.restlet.Context;

/**
 * web application
 * 
 * @author brad.wu
 */
public class WebApplication extends Application {
	public WebApplication() {
	}

	public WebApplication(Context context) {
		super(context);
	}
	//
	// /**
	// * (non-Javadoc)
	// *
	// * @see org.restlet.Application#createInboundRoot()
	// */
	// @Override
	// public Restlet createInboundRoot() {
	// Router router = new Router(getContext());
	// router.attach("/hello", HelloResource.class);
	// return router;
	// }
}
