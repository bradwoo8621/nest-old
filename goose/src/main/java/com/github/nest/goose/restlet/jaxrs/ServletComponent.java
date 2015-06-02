/**
 * 
 */
package com.github.nest.goose.restlet.jaxrs;

/**
 * servlet component
 * 
 * @author brad.wu
 * @author yi.feng
 */
public class ServletComponent extends Component {
	/**
	 * do nothing. since servlet need set uri pattern prefix and call
	 * {@linkplain #initApplications()} manually
	 * 
	 * @see com.github.nest.goose.restlet.jaxrs.Component#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
	}
}
