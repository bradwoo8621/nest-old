/**
 * 
 */
package com.github.nest.goose;

import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.ext.servlet.ServerServlet;
import org.springframework.context.ApplicationContext;

/**
 * spring based restlet servlet<br>
 * 
 * <pre>
 * &lt;servlet&gt;
 * 	&lt;servlet-name&gt;goose&lt;/servlet-name&gt;
 * 	&lt;servlet-class&gt;com.github.nest.goose.SpringServlet&lt;/servlet-class&gt;
 * 	&lt;init-param&gt; 
 * 		&lt;param-name&gt;spring.context.location&lt;/param-name&gt;
 * 		&lt;param-value&gt;classpath:com/github/nest/goose/spring-context.xml&lt;/param-value&gt;
 * 	&lt;/init-param&gt; 
 * &lt;/servlet&gt; 
 * &lt;servlet-mapping&gt;
 * 	&lt;servlet-name&gt;goose&lt;/servlet-name&gt; 
 * 	&lt;url-pattern&gt;/app/*&lt;/url-pattern&gt;
 * &lt;/servlet-mapping&gt;
 * </pre>
 * 
 * and in sprint-context.xml, needs a bean extends from
 * {@linkplain org.restlet.Application}
 * 
 * @author brad.wu
 */
public class SpringServlet extends ServerServlet {
	private static final long serialVersionUID = -4833708753876623002L;

	public final static String SPRING_CONTEXT_ID = SpringServlet.class.getName() + "#springContextId";

	public static final String SPRING_CONFIG_LOCATION_ATTRIBUTE = "spring.context.location";

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.ext.servlet.ServerServlet#createApplication(org.restlet.Context)
	 */
	@Override
	protected Application createApplication(Context parentContext) {
		ApplicationContext springContext = com.github.nest.arcteryx.context.Context.getContext(SPRING_CONTEXT_ID);
		Application application = springContext.getBean(Application.class);
		application.setContext(parentContext.createChildContext());
		return application;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.restlet.ext.servlet.ServerServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();

		String configLocation = getInitParameter(SPRING_CONFIG_LOCATION_ATTRIBUTE, null);
		this.createSpringContext(configLocation);
	}

	/**
	 * create spring context
	 */
	protected void createSpringContext(String configLocation) {
		if (StringUtils.startsWith(configLocation, "classpath:/")) {
			com.github.nest.arcteryx.context.Context.createApplicationContextByClassPath(SPRING_CONTEXT_ID,
					StringUtils.replace(configLocation, "classpath:", ""));
		} else if (StringUtils.startsWith(configLocation, "classpath:")) {
			com.github.nest.arcteryx.context.Context.createApplicationContextByClassPath(SPRING_CONTEXT_ID,
					StringUtils.replace(configLocation, "classpath:", "/"));
		} else {
			com.github.nest.arcteryx.context.Context.createContextByFileSystem(SPRING_CONTEXT_ID, configLocation);
		}
	}
}
