/**
 * 
 */
package com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.convertors;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Payload;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.cfg.ConstraintDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nest.arcteryx.meta.beans.ConstraintSeverity;
import com.github.nest.arcteryx.meta.beans.IBeanPropertyConstraint;
import com.github.nest.arcteryx.meta.beans.IConstraint;
import com.github.nest.arcteryx.meta.beans.internal.validators.BeanValidationException;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.HibernateSeverity;
import com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor;

/**
 * abstract hibernate constraint convertor
 * 
 * @author brad.wu
 */
public abstract class AbstractHibernateConstraintConvertor<C extends IConstraint> implements
		IHibernateConstraintConvertor<C> {
	private static Logger logger = LoggerFactory.getLogger(AbstractHibernateConstraintConvertor.class);
	static {
		logger.warn("Note: For Hibernate Validator 5.1.x, some properties are not supported yet.");
		logger.error("Note: Property \"when\"/\"target\" is not supported yet, will throw BeanValidationException.");
		logger.warn("Note: Property \"errorCode\" is not supported yet, will be skipped.");
		logger.warn("Note: Property \"appliesTo\" is not supported yet, will be skipped.");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.beans.internal.validators.hibernate.IHibernateConstraintConvertor#convert(com.github.nest.arcteryx.meta.beans.IConstraint)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ConstraintDef convert(C constraint) {
		ConstraintDef def = createConstraintDef(constraint);

		def.payload(convertSeverity(constraint));
		def.groups(convertGroups(constraint));
		if (!StringUtils.isBlank(constraint.getMessageTemplate())) {
			def.message(constraint.getMessageTemplate());
		}

		if (StringUtils.isNotEmpty(constraint.getWhen())) {
			throw new BeanValidationException("Hibernate validation doesn't support \"when\" yet.");
		}
		if (constraint instanceof IBeanPropertyConstraint) {
			IBeanPropertyConstraint cons = (IBeanPropertyConstraint) constraint;
			if (StringUtils.isNotEmpty(cons.getTarget())) {
				throw new BeanValidationException("Hibernate validation doesn't support \"target\" yet.");
			}
		}

		return def;
	}

	/**
	 * convert groups
	 * 
	 * @param constraint
	 * @return
	 */
	protected Class<?>[] convertGroups(C constraint) {
		String[] profiles = constraint.getProfiles();

		if (profiles == null || profiles.length == 0) {
			return new Class<?>[] { Default.class };
		} else {
			List<Class<?>> groups = new LinkedList<Class<?>>();
			groups.add(Default.class);
			for (String profile : profiles) {
				try {
					groups.add(Class.forName(profile));
				} catch (ClassNotFoundException e) {
					throw new BeanValidationException("Cannot convert profile[" + profile + "] to group class.");
				}
			}
			return groups.toArray(new Class<?>[groups.size()]);
		}
	}

	/**
	 * convert severity
	 * 
	 * @param constraint
	 * @return
	 */
	protected Class<? extends Payload> convertSeverity(C constraint) {
		ConstraintSeverity severity = constraint.getSeverity();
		if (ConstraintSeverity.INFO.equals(severity)) {
			return HibernateSeverity.INFO.class;
		} else if (ConstraintSeverity.WARN.equals(severity)) {
			return HibernateSeverity.WARN.class;
		} else if (ConstraintSeverity.ERROR.equals(severity)) {
			return HibernateSeverity.ERROR.class;
		} else if (ConstraintSeverity.FATAL.equals(severity)) {
			return HibernateSeverity.FATAL.class;
		} else {
			return HibernateSeverity.INFO.class;
		}
	}

	/**
	 * create constraint def
	 * 
	 * @param constraint
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected abstract ConstraintDef createConstraintDef(C constraint);
}
