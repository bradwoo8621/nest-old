/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate;

import java.util.HashSet;
import java.util.Set;

import com.github.nest.arcteryx.persistent.IPrimaryKeyGenerator;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.HiloKey;
import com.github.nest.arcteryx.persistent.internal.hibernate.pkgenerator.SequenceHiloKey;

/**
 * generator of primary key generator
 * 
 * @author brad.wu
 */
public interface IPrimaryKeyGeneratorGenerator<T extends IPrimaryKeyGenerator> {
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

	/**
	 * @see HiloKey
	 * @author brad.wu
	 */
	public static class HiloGenerator implements IPrimaryKeyGeneratorGenerator<HiloKey> {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGeneratorGenerator#getSupportedType()
		 */
		@Override
		public Class<HiloKey> getSupportedType() {
			return HiloKey.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGeneratorGenerator#getGeneratorClass(com.github.nest.arcteryx.persistent.IPrimaryKeyGenerator)
		 */
		@Override
		public String getGeneratorClass(HiloKey generator) {
			return "hilo";
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGeneratorGenerator#getParameters(com.github.nest.arcteryx.persistent.IPrimaryKeyGenerator)
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
	 * @see HiloKey
	 * @author brad.wu
	 */
	public static class SequenceHiloGenerator implements IPrimaryKeyGeneratorGenerator<SequenceHiloKey> {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGeneratorGenerator#getSupportedType()
		 */
		@Override
		public Class<SequenceHiloKey> getSupportedType() {
			return SequenceHiloKey.class;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGeneratorGenerator#getGeneratorClass(com.github.nest.arcteryx.persistent.IPrimaryKeyGenerator)
		 */
		@Override
		public String getGeneratorClass(SequenceHiloKey generator) {
			return "seqhilo";
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGeneratorGenerator#getParameters(com.github.nest.arcteryx.persistent.IPrimaryKeyGenerator)
		 */
		@Override
		public Set<GeneratorParameter> getParameters(SequenceHiloKey generator) {
			Set<GeneratorParameter> params = new HashSet<GeneratorParameter>();
			params.add(new GeneratorParameter("sequence", generator.getSequenceName()));
			params.add(new GeneratorParameter("max_lo", String.valueOf(generator.getMaxLowValue())));
			return params;
		}
	}

	public static class PrimaryKeyGeneratorUtils {
		@SuppressWarnings("rawtypes")
		private final static Set<IPrimaryKeyGeneratorGenerator> generators = new HashSet<IPrimaryKeyGeneratorGenerator>();

		static {
			generators.add(new HiloGenerator());
			generators.add(new SequenceHiloGenerator());
		}

		@SuppressWarnings("rawtypes")
		public static Set<IPrimaryKeyGeneratorGenerator> predefinedGenerators() {
			return generators;
		}
	}
}
