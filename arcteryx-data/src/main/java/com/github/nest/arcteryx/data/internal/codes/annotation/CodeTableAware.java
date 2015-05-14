/**
 * 
 */
package com.github.nest.arcteryx.data.internal.codes.annotation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.github.nest.arcteryx.data.IArcteryxDataExceptionCodes;
import com.github.nest.arcteryx.data.codes.CodesRuntimeException;
import com.github.nest.arcteryx.data.codes.ICodeTable;
import com.github.nest.arcteryx.data.codes.ICodeTableRegistry;
import com.github.nest.arcteryx.data.codes.ICodeTableScanner;

/**
 * Code table aware
 * 
 * @author brad.wu
 */
public class CodeTableAware implements ApplicationContextAware, InitializingBean {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ApplicationContext applicationContext;
	private ICodeTableRegistry registry = null;
	private List<ICodeTableScanner> scanners = null;

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		scanByScanners();
		ApplicationContext context = this.getApplicationContext();
		String[] names = context.getBeanNamesForAnnotation(CodeTable.class);
		if (names == null || names.length == 0) {
			if (logger.isInfoEnabled()) {
				logger.info("No class annotated by [" + CodeTable.class.getName() + "].");
			}
			return;
		}

		Set<Class<? extends ICodeTable>> invalidClasses = new HashSet<Class<? extends ICodeTable>>();
		for (String name : names) {
			// the code table class is replaced by another class
			Class<? extends ICodeTable> codeTableClass = (Class<? extends ICodeTable>) context.getType(name);
			CodeTableReplacement replacement = codeTableClass.getAnnotation(CodeTableReplacement.class);
			if (replacement != null) {
				Class<? extends ICodeTable> replacedClass = replacement.replace();
				invalidClasses.add(replacedClass);
			}
		}

		for (String name : names) {
			Class<? extends ICodeTable> codeTableClass = (Class<? extends ICodeTable>) context.getType(name);
			if (invalidClasses.contains(codeTableClass)) {
				// skip the code table which was replaced by another
				if (logger.isInfoEnabled()) {
					logger.info("CodeTable[" + codeTableClass + "] was replaced and ignored.");
				}
				continue;
			}
			// construct code table
			ICodeTable codeTable = createCodeTable(codeTableClass);
			// check the code table was registered or not
			if (this.getRegistry().getCodeTable(codeTable.getName()) != null) {
				if (logger.isWarnEnabled()) {
					logger.warn("CodeTable[" + codeTable.getName()
							+ "] already registered(By scanner or annotation), was ignored.");
				}
			} else {
				this.getRegistry().register(codeTable);
			}
			if (logger.isInfoEnabled()) {
				logger.info("CodeTable[" + codeTable.getName() + "] defined with [" + codeTableClass
						+ "] was registered.");
			}
		}
	}

	/**
	 * create code table
	 * 
	 * @param codeTableClass
	 * @return
	 */
	protected ICodeTable createCodeTable(Class<? extends ICodeTable> codeTableClass) {
		// must have a constructor with no parameter
		try {
			return codeTableClass.newInstance();
		} catch (Exception e) {
			throw new CodesRuntimeException(IArcteryxDataExceptionCodes.CODE_TABLE_CONSTRUCT,
					"Failed to construct code table[" + codeTableClass + "].", e);
		}
	}

	/**
	 * scan code table by scanners
	 */
	protected void scanByScanners() {
		if (this.getScanners() != null) {
			for (ICodeTableScanner scanner : this.getScanners()) {
				if (logger.isInfoEnabled()) {
					logger.info("Start to scan code table by given scanner[" + scanner + "].");
				}

				scanner.scan(this.getRegistry());
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @return the registry
	 */
	public ICodeTableRegistry getRegistry() {
		return registry;
	}

	/**
	 * @param registry
	 *            the registry to set
	 */
	public void setRegistry(ICodeTableRegistry registry) {
		this.registry = registry;
	}

	/**
	 * @return the scanners
	 */
	public List<ICodeTableScanner> getScanners() {
		return scanners;
	}

	/**
	 * @param scanners
	 *            the scanners to set
	 */
	public void setScanners(List<ICodeTableScanner> scanners) {
		this.scanners = scanners;
	}
}
