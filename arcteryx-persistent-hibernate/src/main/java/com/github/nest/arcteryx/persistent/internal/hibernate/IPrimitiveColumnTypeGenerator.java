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

	public static class GenericGenerator implements IPrimitiveColumnTypeGenerator {
		private String typeString = null;
		private PrimitiveColumnType supportedType = null;

		public GenericGenerator(String typeString, PrimitiveColumnType supportedType) {
			this.typeString = typeString;
			this.supportedType = supportedType;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimitiveColumnTypeGenerator#generate(com.github.nest.arcteryx.persistent.PrimitiveColumnType)
		 */
		@Override
		public String generate(PrimitiveColumnType type) {
			return this.typeString;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see com.github.nest.arcteryx.persistent.internal.hibernate.IPrimitiveColumnTypeGenerator#getSupportedType()
		 */
		@Override
		public PrimitiveColumnType getSupportedType() {
			return this.supportedType;
		}
	}

	public static class PrimitiveColumnTypeGeneratorUtils {
		private final static Set<IPrimitiveColumnTypeGenerator> generators = new HashSet<IPrimitiveColumnTypeGenerator>();

		static {
			generators.add(new GenericGenerator("integer", PrimitiveColumnType.INT));
			generators.add(new GenericGenerator("short", PrimitiveColumnType.SHORT));
			generators.add(new GenericGenerator("long", PrimitiveColumnType.LONG));
			generators.add(new GenericGenerator("float", PrimitiveColumnType.FLOAT));
			generators.add(new GenericGenerator("double", PrimitiveColumnType.DOUBLE));
			generators.add(new GenericGenerator("big_decimal", PrimitiveColumnType.BIGDECIMAL));

			generators.add(new GenericGenerator("date", PrimitiveColumnType.DATE));
			generators.add(new GenericGenerator("time", PrimitiveColumnType.TIME));
			generators.add(new GenericGenerator("timestamp", PrimitiveColumnType.TIMESTAMP));

			generators.add(new GenericGenerator("string", PrimitiveColumnType.STRING));
			generators.add(new GenericGenerator("text", PrimitiveColumnType.BIGSTRING));

			generators.add(new GenericGenerator("boolean", PrimitiveColumnType.BOOLEAN));

			generators.add(new GenericGenerator("byte", PrimitiveColumnType.BYTES));

			generators.add(new GenericGenerator("version", PrimitiveColumnType.LONG));
			generators.add(new GenericGenerator("timestamp", PrimitiveColumnType.VERSION_TIMESTAMP));
		}

		public static Set<IPrimitiveColumnTypeGenerator> predefinedGenerators() {
			return generators;
		}
	}
}