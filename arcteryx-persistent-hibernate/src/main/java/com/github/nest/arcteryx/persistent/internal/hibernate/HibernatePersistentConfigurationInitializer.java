/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nest.arcteryx.meta.IResourceDescriptorContext;
import com.github.nest.arcteryx.meta.ResourceException;
import com.github.nest.arcteryx.persistent.IEmbeddedPersistentColumn;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentColumn;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.IPrimaryKey;
import com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn;
import com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.PrimitiveColumnType;
import com.github.nest.arcteryx.persistent.internal.PersistentConfiguration;
import com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGenerator.GeneratorParameter;
import com.github.nest.arcteryx.persistent.internal.hibernate.IPrimaryKeyGenerator.PrimaryKeyGeneratorUtils;
import com.github.nest.arcteryx.persistent.internal.hibernate.IPrimitiveColumnTypeGenerator.PrimitiveColumnTypeGeneratorUtils;

/**
 * hibernate configuration initializer
 * 
 * @author brad.wu
 */
public class HibernatePersistentConfigurationInitializer implements IPersistentConfigurationInitializer {
	private final class PropertyComparator implements Comparator<IPersistentBeanPropertyDescriptor> {
		/**
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(IPersistentBeanPropertyDescriptor o1, IPersistentBeanPropertyDescriptor o2) {
			return getValueOfProperty(o1) - getValueOfProperty(o2);
		}

		private int getValueOfProperty(IPersistentBeanPropertyDescriptor descriptor) {
			IPersistentColumn column = descriptor.getPersistentColumn();
			if (column instanceof IPrimitivePersistentColumn) {
				IPrimitivePersistentColumn ippc = (IPrimitivePersistentColumn) column;
				if (ippc.isPrimaryKey()) {
					return 1;
				} else if (ippc.isVersion()) {
					return 2;
				} else {
					return 3;
				}
			} else {
				return 999;
			}
		}
	}

	private static final String HIBERNATE_XML_SYSTEM_ID = "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd";
	private static final String HIBERNATE_XML_NAME = "hibernate-mapping";
	private static final String HIBERNATE_XML_PUBLIC_ID = "-//Hibernate/Hibernate Mapping DTD 3.0//EN";

	private static final long serialVersionUID = -5380074460354685470L;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private Properties properties = null;
	private Map<PrimitiveColumnType, IPrimitiveColumnTypeGenerator> primitiveColumnTypeGenerators = new HashMap<PrimitiveColumnType, IPrimitiveColumnTypeGenerator>();
	@SuppressWarnings("rawtypes")
	private Map<Class<? extends IPrimaryKey>, IPrimaryKeyGenerator> primaryKeyGenerators = new HashMap<Class<? extends IPrimaryKey>, IPrimaryKeyGenerator>();

	public HibernatePersistentConfigurationInitializer() {
		initializePrimitiveColumnTypeGenerators();
		initializePrimaryKeyGenerators();
	}

	/**
	 * initialize primary key generators
	 */
	@SuppressWarnings("rawtypes")
	protected void initializePrimaryKeyGenerators() {
		Set<IPrimaryKeyGenerator> generators = PrimaryKeyGeneratorUtils.predefinedGenerators();
		for (IPrimaryKeyGenerator generator : generators) {
			this.addPrimaryKeyGenerator(generator);
		}
	}

	/**
	 * get primary key generator
	 * 
	 * @param type
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected IPrimaryKeyGenerator getPrimaryKeyGenerator(IPrimaryKey type) {
		return primaryKeyGenerators.get(type.getClass());
	}

	/**
	 * @param primaryKeyGenerators
	 */
	@SuppressWarnings("rawtypes")
	public void setPrimaryKeyGenerators(Map<Class<? extends IPrimaryKey>, IPrimaryKeyGenerator> primaryKeyGenerators) {
		this.primaryKeyGenerators = primaryKeyGenerators;
	}

	/**
	 * add primary key generator
	 * 
	 * @param generator
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addPrimaryKeyGenerator(IPrimaryKeyGenerator generator) {
		this.primaryKeyGenerators.put(generator.getSupportedType(), generator);
	}

	/**
	 * initialize primitive column type generators
	 */
	protected void initializePrimitiveColumnTypeGenerators() {
		Set<IPrimitiveColumnTypeGenerator> generators = PrimitiveColumnTypeGeneratorUtils.predefinedGenerators();
		for (IPrimitiveColumnTypeGenerator generator : generators) {
			this.addPrimitiveColumnTypeGenerator(generator);
		}
	}

	/**
	 * get primitive column type generator by given type
	 * 
	 * @param type
	 * @return
	 */
	protected IPrimitiveColumnTypeGenerator getPrimitiveColumnTypeGenerator(PrimitiveColumnType type) {
		return primitiveColumnTypeGenerators.get(type);
	}

	/**
	 * @param primitiveColumnTypeGenerators
	 *            the primitiveColumnTypeGenerators to set
	 */
	public void setPrimitiveColumnTypeGenerators(
			Map<PrimitiveColumnType, IPrimitiveColumnTypeGenerator> primitiveColumnTypeGenerators) {
		this.primitiveColumnTypeGenerators = primitiveColumnTypeGenerators;
	}

	/**
	 * add primitive column type generator
	 * 
	 * @param generator
	 */
	public void addPrimitiveColumnTypeGenerator(IPrimitiveColumnTypeGenerator generator) {
		this.primitiveColumnTypeGenerators.put(generator.getSupportedType(), generator);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IConfigurationInitializer#initialize(com.github.nest.arcteryx.meta.IResourceDescriptorContext)
	 */
	@Override
	public IPersistentConfiguration initialize(IResourceDescriptorContext context) {
		Configuration configuration = new Configuration();
		Collection<IPersistentBeanDescriptor> descriptors = context.getDescriptors(IPersistentBeanDescriptor.class);
		if (descriptors != null) {
			for (IPersistentBeanDescriptor descriptor : descriptors) {
				if (descriptor instanceof IStandalonePersistentBeanDescriptor) {
					configuration.addXML(convertToXML((IStandalonePersistentBeanDescriptor) descriptor));
				}
			}
		}
		Properties properties = this.getProperties();
		if (properties != null) {
			configuration.addProperties(properties);
		}

		return new PersistentConfiguration(configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build()));
	}

	/**
	 * convert definition to hibernate xml
	 * 
	 * @param descriptor
	 * @return
	 * @throws IOException
	 */
	protected String convertToXML(IStandalonePersistentBeanDescriptor descriptor) {
		Document doc = createDocument(descriptor);

		StringWriter sw = new StringWriter();
		XMLWriter writer = new XMLWriter(sw, OutputFormat.createCompactFormat());
		try {
			writer.write(doc);
		} catch (IOException e) {
			if (getLogger().isErrorEnabled()) {
				getLogger().error("Failed to cast xml document to string.", e);
			}
			throw new ResourceException("Failed to cast xml document to string.", e);
		}
		if (getLogger().isDebugEnabled()) {
			getLogger().debug(
					"Class [" + descriptor.getResourceClass().getName()
							+ "] has been configured into hibernate. XML as [" + sw.toString() + "].");
		}
		return sw.toString();
	}

	/**
	 * create xml document
	 * 
	 * @param descriptor
	 * @return
	 */
	protected Document createDocument(IStandalonePersistentBeanDescriptor descriptor) {
		Document doc = DocumentHelper.createDocument();
		doc.addDocType(HIBERNATE_XML_NAME, HIBERNATE_XML_PUBLIC_ID, HIBERNATE_XML_SYSTEM_ID);

		Element root = createRootElement(descriptor, doc);
		doc.setRootElement(root);

		Element classElement = createClassElement(descriptor);
		root.add(classElement);

		return doc;
	}

	/**
	 * create class element
	 * 
	 * @param descriptor
	 * @return
	 */
	protected Element createClassElement(IStandalonePersistentBeanDescriptor descriptor) {
		Element classElement = DocumentHelper.createElement("class");
		classElement.addAttribute("name", descriptor.getBeanClass().getSimpleName());
		classElement.addAttribute("table", descriptor.getTableName());

		List<Element> propertyElements = createPropertyElements(descriptor);
		for (Element propertyElement : propertyElements) {
			classElement.add(propertyElement);
		}
		return classElement;
	}

	/**
	 * create property elements
	 * 
	 * @param descriptor
	 * @return
	 */
	protected List<Element> createPropertyElements(IPersistentBeanDescriptor descriptor) {
		Collection<IPersistentBeanPropertyDescriptor> properties = descriptor.getPersistentProperties();
		List<IPersistentBeanPropertyDescriptor> list = new ArrayList<IPersistentBeanPropertyDescriptor>(
				properties.size());
		list.addAll(properties);
		// id first, version second.
		Collections.sort(list, new PropertyComparator());
		List<Element> propertyElements = new LinkedList<Element>();
		for (IPersistentBeanPropertyDescriptor property : list) {
			propertyElements.add(createPropertyElement(property));
		}
		return propertyElements;
	}

	/**
	 * create property element
	 * 
	 * @param property
	 * @return
	 */
	protected Element createPropertyElement(IPersistentBeanPropertyDescriptor property) {
		IPersistentColumn column = property.getPersistentColumn();

		if (column instanceof IPrimitivePersistentColumn) {
			return createPrimitiveColumnElement(property, (IPrimitivePersistentColumn) column);
		} else if (column instanceof IEmbeddedPersistentColumn) {
			return createEmbeddedColumnElement(property, (IEmbeddedPersistentColumn) column);
		} else {
			throw new ResourceException("Property [" + property.getResourceDescriptor().getResourceClass().getName()
					+ "#" + property.getName() + "] cannot be convert to hibernate xml.");
		}
	}

	/**
	 * create embedded property element
	 * 
	 * @param property
	 * @param embeddedColumn
	 * @return
	 */
	protected Element createEmbeddedColumnElement(IPersistentBeanPropertyDescriptor property,
			IEmbeddedPersistentColumn embeddedColumn) {
		Element componentElement = DocumentHelper.createElement("component");
		// property name
		componentElement.addAttribute("name", property.getName());

		IPersistentBeanDescriptor embeddedBean = embeddedColumn.getEmbeddedBean();
		componentElement.addAttribute("class", embeddedBean.getBeanClass().getName());
		// TODO how to override the column names?
		List<Element> propertyElements = createPropertyElements(embeddedBean);
		for (Element propertyElement : propertyElements) {
			componentElement.add(propertyElement);
		}
		return componentElement;
	}

	/**
	 * create primitive property element
	 * 
	 * @param property
	 * @param column
	 * @return
	 */
	protected Element createPrimitiveColumnElement(IPersistentBeanPropertyDescriptor property,
			IPrimitivePersistentColumn primitiveColumn) {
		// primitive column
		Element propertyElement = null;
		if (primitiveColumn.isPrimaryKey()) {
			// primary key column
			propertyElement = DocumentHelper.createElement("id");
			propertyElement.add(createPrimaryKeyGeneratorElement(primitiveColumn));
			// column type
			propertyElement.addAttribute("type", generateTypeString(primitiveColumn));
		} else if (primitiveColumn.isVersion()) {
			// optimistic lock column
			PrimitiveColumnType type = primitiveColumn.getType();
			if (type == PrimitiveColumnType.TIMESTAMP) {
				// timestamp optimistic lock
				propertyElement = DocumentHelper.createElement("timestamp");
			} else {
				// version optimistic lock
				propertyElement = DocumentHelper.createElement("version");
				// column type
				propertyElement.addAttribute("type", generateTypeString(primitiveColumn));
			}
		} else {
			// normal primitive column
			propertyElement = DocumentHelper.createElement("property");
			// column type
			propertyElement.addAttribute("type", generateTypeString(primitiveColumn));
		}
		// column name
		propertyElement.addAttribute("column", primitiveColumn.getName());
		// property name
		propertyElement.addAttribute("name", property.getName());
		return propertyElement;
	}

	/**
	 * generate type string
	 * 
	 * @param primitiveColumn
	 * @return
	 */
	protected String generateTypeString(IPrimitivePersistentColumn primitiveColumn) {
		PrimitiveColumnType type = primitiveColumn.getType();
		return this.getPrimitiveColumnTypeGenerator(type).generate(type);
	}

	/**
	 * create primary key generator element
	 * 
	 * @param primitiveColumn
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Element createPrimaryKeyGeneratorElement(IPrimitivePersistentColumn primitiveColumn) {
		IPrimaryKey generator = primitiveColumn.getPrimaryKeyGenerator();
		IPrimaryKeyGenerator pkGenerator = this.getPrimaryKeyGenerator(generator);
		Element generatorElement = DocumentHelper.createElement("generator");
		generatorElement.addAttribute("class", pkGenerator.getGeneratorClass(generator));
		Set<GeneratorParameter> params = pkGenerator.getParameters(generator);
		if (params != null) {
			for (GeneratorParameter param : params) {
				String value = param.getValue();
				if (StringUtils.isNotBlank(value)) {
					Element paramElement = DocumentHelper.createElement("param");
					paramElement.addAttribute("name", param.getName());
					paramElement.setText(param.getValue());
					generatorElement.add(paramElement);
				}
			}
		}
		return generatorElement;
	}

	/**
	 * create root element
	 * 
	 * @param descriptor
	 * @param doc
	 * @return
	 */
	protected Element createRootElement(IPersistentBeanDescriptor descriptor, Document doc) {
		Element root = DocumentHelper.createElement(HIBERNATE_XML_NAME);
		root.addAttribute("package", descriptor.getBeanClass().getPackage().getName());
		return root;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.github.nest.arcteryx.meta.IConfigurationInitializer#getReturnValueKey()
	 */
	@Override
	public String getReturnValueKey() {
		return KEY;
	}

	/**
	 * @return the logger
	 */
	protected Logger getLogger() {
		return logger;
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * add property
	 * 
	 * @param key
	 * @param value
	 */
	public void addProperty(Object key, Object value) {
		if (this.properties == null) {
			synchronized (this) {
				if (this.properties == null) {
					this.properties = new Properties();
				}
			}
		}
		this.properties.put(key, value);
	}
}
