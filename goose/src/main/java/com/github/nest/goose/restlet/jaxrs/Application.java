/**
 * 
 */
package com.github.nest.goose.restlet.jaxrs;

import java.util.List;

import org.restlet.Restlet;
import org.restlet.ext.jaxrs.JaxRsApplication;
import org.restlet.routing.Filter;

/**
 * application
 * 
 * @author brad.wu
 * @author yi.feng
 */
public class Application extends JaxRsApplication {
	private List<Filter> filters = null;

	/**
	 * @return the filters
	 */
	public List<Filter> getFilters() {
		return filters;
	}

	/**
	 * @param filters
	 *            the filters to set
	 */
	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.ext.jaxrs.JaxRsApplication#createInboundRoot()
	 */
	@Override
	public Restlet createInboundRoot() {
		Restlet root = super.createInboundRoot();
		if (this.getFilters() != null) {
			Restlet next = root;
			for (int index = this.getFilters().size() - 1; index >= 0; index--) {
				Filter filter = this.getFilters().get(index);
				filter.setNext(next);
				next = filter;
			}
			return next;
		} else {
			return root;
		}
	}
}
