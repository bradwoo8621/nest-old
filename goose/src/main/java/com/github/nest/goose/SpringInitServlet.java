/**
 * 
 */
package com.github.nest.goose;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import com.github.nest.arcteryx.context.Context;
import com.github.nest.goose.util.PathUtils;

/**
 * spring initialize servlet
 * 
 * @author brad.wu
 */
public class SpringInitServlet extends HttpServlet {
	private static final long serialVersionUID = 264782831977571021L;
	public static final String SPRING_CONFIG_FILE_ATTRIBUTE = "spring.config.file";
	private static final String SPRING_CONTEXT_ID = "root";

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();

		String configFile = this.getServletConfig().getInitParameter(SPRING_CONFIG_FILE_ATTRIBUTE);
		if (StringUtils.isBlank(configFile)) {
			throw new ServletException(MessageFormatter.format("[{}] cannot be blank.", SPRING_CONFIG_FILE_ATTRIBUTE)
					.getMessage());
		}

		loadRootContext(configFile);
	}

	/**
	 * load root context
	 * 
	 * @param configFile
	 */
	protected void loadRootContext(String configFile) {
		if (PathUtils.isClasspath(configFile)) {
			// load from classpath
			Context.createContextByClassPath(SPRING_CONTEXT_ID, PathUtils.getAbsoluteClasspath(configFile));
		} else {
			// file system
			Context.createContextByFileSystem(SPRING_CONTEXT_ID, configFile);
		}

		// trigger initialize, for lazy-init
		Context.getContext(SPRING_CONTEXT_ID).getBean(SpringContextConfiguration.class);
	}
}
