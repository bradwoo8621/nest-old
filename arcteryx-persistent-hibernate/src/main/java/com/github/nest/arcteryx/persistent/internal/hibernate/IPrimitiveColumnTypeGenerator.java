package com.github.nest.arcteryx.persistent.internal.hibernate;

import java.util.HashSet;
import java.util.Set;

import com.github.nest.arcteryx.persistent.PrimitiveColumnType;

/**
 * primitive column type generator
 * 
 * @author brad.wu
 *
 */
public interface IPrimitiveColumnTypeGenerator {
	/**
	 * generate type to string
	 * 
	 * @param type
	 * @return
	 */
	String generate(PrimitiveColumnType type);

	/**
	 * get supported type
	 * 
	 * @return
	 */
	PrimitiveColumnType getSupportedType();

	public static class LongGenerator implements IPrimitiveColumnTypeGenerator {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimitiveColumnTypeGenerator#generate(com.github.nest.arcteryx.persistent.PrimitiveColumnType)
		 */
		@Override
		public String generate(PrimitiveColumnType type) {
			return "long";
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimitiveColumnTypeGenerator#getSupportedType()
		 */
		@Override
		public PrimitiveColumnType getSupportedType() {
			return PrimitiveColumnType.LONG;
		}
	}

	public static class StringGenerator implements IPrimitiveColumnTypeGenerator {
		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimitiveColumnTypeGenerator#generate(com.github.nest.arcteryx.persistent.PrimitiveColumnType)
		 */
		@Override
		public String generate(PrimitiveColumnType type) {
			return "string";
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimitiveColumnTypeGenerator#getSupportedType()
		 */
		@Override
		public PrimitiveColumnType getSupportedType() {
			return PrimitiveColumnType.STRING;
		}
	}

	public static class PrimitiveColumnTypeGeneratorUtils {
		private final static Set<IPrimitiveColumnTypeGenerator> generators = new HashSet<IPrimitiveColumnTypeGenerator>();

		static {
			generators.add(new LongGenerator());

			generators.add(new StringGenerator());
		}

		public static Set<IPrimitiveColumnTypeGenerator> predefinedGenerators() {
			return generators;
		}
	}
}