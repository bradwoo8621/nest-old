/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.github.nest.arcteryx.persistent.IPrimaryKey;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.GuidKey;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.HiloKey;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.IdentityKey;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.IncrementKey;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.SequenceHiloKey;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.SequenceKey;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.UuidKey;

/**
 * generator of primary key generator
 * 
 * @author brad.wu
 */
public interface IPrimaryKeyGenerator<T extends IPrimaryKey> {
	/**
	 * get supported type
	 * 
	 * @return
	 */
	Class<T> getSupportedType();

	/**
	 * get generator class by given generator
	 * 
	 * @param generator
	 * @return
	 */
	String getGeneratorClass(T generator);

	/**
	 * get generator parameters by given generator
	 * 
	 * @param generator
	 * @return
	 */
	Set<GeneratorParameter> getParameters(T generator);

	/**
	 * generator parameter
	 * 
	 * @author brad.wu
	 */
	public static class GeneratorParameter {
		private String name = null;
		private String value = null;

		public GeneratorParameter(String name, String value) {
			this.name = name;
			this.value = value;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}
	}

	public static class GenericPrimaryKeyGenerator<T extends IPrimaryKey> implements IPrimaryKeyGenerator<T> {
		private Class<T> supportedType = null;
		private String generatorClassName = null;

		public GenericPrimaryKeyGenerator(Class<T> supportedType, String generatorClassName) {
			this.supportedType = supportedType;
			this.generatorClassName = generatorClassName;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGenerator#getSupportedType()
		 */
		@Override
		public Class<T> getSupportedType() {
			return this.supportedType;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGenerator#getGeneratorClass(com.github.nest.arcteryx.persistent.IPrimaryKey)
		 */
		@Override
		public String getGeneratorClass(T generator) {
			return this.generatorClassName;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGenerator#getParameters(com.github.nest.arcteryx.persistent.IPrimaryKey)
		 */
		@Override
		public Set<GeneratorParameter> getParameters(T generator) {
			return Collections.emptySet();
		}
	}

	/**
	 * @see HiloKey
	 * @author brad.wu
	 */
	public static class HiloGenerator extends GenericPrimaryKeyGenerator<HiloKey> {
		public HiloGenerator() {
			super(HiloKey.class, "hilo");
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGenerator#getParameters(com.github.nest.arcteryx.persistent.IPrimaryKey)
		 */
		@Override
		public Set<GeneratorParameter> getParameters(HiloKey generator) {
			Set<GeneratorParameter> params = new HashSet<GeneratorParameter>();
			params.add(new GeneratorParameter("table", generator.getTableName()));
			params.add(new GeneratorParameter("column", generator.getColumnName()));
			params.add(new GeneratorParameter("max_lo", String.valueOf(generator.getMaxLowValue())));
			return params;
		}
	}

	/**
	 * @see SequenceHiloKey
	 * @author brad.wu
	 */
	public static class SequenceHiloGenerator extends GenericPrimaryKeyGenerator<SequenceHiloKey> {
		public SequenceHiloGenerator() {
			super(SequenceHiloKey.class, "seqhilo");
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGenerator#getParameters(com.github.nest.arcteryx.persistent.IPrimaryKey)
		 */
		@Override
		public Set<GeneratorParameter> getParameters(SequenceHiloKey generator) {
			Set<GeneratorParameter> params = new HashSet<GeneratorParameter>();
			params.add(new GeneratorParameter("sequence", generator.getSequenceName()));
			params.add(new GeneratorParameter("max_lo", String.valueOf(generator.getMaxLowValue())));
			return params;
		}
	}

	/**
	 * @see SequenceKey
	 * @author brad.wu
	 */
	public static class SequenceGenerator extends GenericPrimaryKeyGenerator<SequenceKey> {
		public SequenceGenerator() {
			super(SequenceKey.class, "sequence");
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGenerator#getParameters(com.github.nest.arcteryx.persistent.IPrimaryKey)
		 */
		@Override
		public Set<GeneratorParameter> getParameters(SequenceKey generator) {
			Set<GeneratorParameter> params = new HashSet<GeneratorParameter>();
			params.add(new GeneratorParameter("sequence", generator.getSequenceName()));
			return params;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static class PrimaryKeyGeneratorUtils {
		private final static Set<IPrimaryKeyGenerator> generators = new HashSet<IPrimaryKeyGenerator>();

		static {
			generators.add(new HiloGenerator());
			generators.add(new SequenceHiloGenerator());
			generators.add(new SequenceGenerator());
			generators.add(new GenericPrimaryKeyGenerator(IncrementKey.class, "increment"));
			generators.add(new GenericPrimaryKeyGenerator(IdentityKey.class, "identity"));
			generators.add(new GenericPrimaryKeyGenerator(UuidKey.class, "uuid"));
			generators.add(new GenericPrimaryKeyGenerator(GuidKey.class, "guid"));
		}

		public static Set<IPrimaryKeyGenerator> predefinedGenerators() {
			return generators;
		}
	}
}
