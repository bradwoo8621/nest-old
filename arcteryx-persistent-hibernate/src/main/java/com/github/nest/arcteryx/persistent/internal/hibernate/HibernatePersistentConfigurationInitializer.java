/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal.hibernate;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
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
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentColumn;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.IPrimaryKey;
import com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn;
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
	public void setPrimaryKeyGenerators(
			Map<Class<? extends IPrimaryKey>, IPrimaryKeyGenerator> primaryKeyGenerators) {
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
				configuration.addXML(convertToXML(descriptor));
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
	protected String convertToXML(IPersistentBeanDescriptor descriptor) {
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
					"Class [" + descriptor.getBeanClass().getName() + "] has been configured into hibernate. XML as ["
							+ sw.toString() + "].");
		}
		return sw.toString();
	}

	/**
	 * create xml document
	 * 
	 * @param descriptor
	 * @return
	 */
	protected Document createDocument(IPersistentBeanDescriptor descriptor) {
		Document doc = DocumentHelper.createDocument();
		doc.addDocType("hibernate-mapping", "-//Hibernate/Hibernate Mapping DTD 3.0//EN",
				"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd");

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
	protected Element createClassElement(IPersistentBeanDescriptor descriptor) {
		Element classElement = DocumentHelper.createElement("class");
		classElement.addAttribute("name", descriptor.getBeanClass().getSimpleName());
		classElement.addAttribute("table", descriptor.getTableName());

		Collection<IPersistentBeanPropertyDescriptor> properties = descriptor.getPersistentProperties();
		for (IPersistentBeanPropertyDescriptor property : properties) {
			classElement.add(createPropertyElement(property));
		}
		return classElement;
	}

	/**
	 * create property element
	 * 
	 * @param property
	 * @return
	 */
	protected Element createPropertyElement(IPersistentBeanPropertyDescriptor property) {
		IPersistentColumn column = property.getPersistentColumn();

		Element propertyElement = null;
		if (column instanceof IPrimitivePersistentColumn) {
			IPrimitivePersistentColumn primitiveColumn = (IPrimitivePersistentColumn) column;
			if (primitiveColumn.isPrimaryKey()) {
				propertyElement = DocumentHelper.createElement("id");
				propertyElement.add(createPrimaryKeyGeneratorElement(primitiveColumn));
			} else {
				propertyElement = DocumentHelper.createElement("property");
			}
			// column name
			propertyElement.addAttribute("column", primitiveColumn.getName());
			// column type
			propertyElement.addAttribute("type", this.getPrimitiveColumnTypeGenerator(primitiveColumn.getType())
					.generate(primitiveColumn.getType()));
		}
		// property name
		propertyElement.addAttribute("name", property.getName());
		return propertyElement;
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
		Element root = DocumentHelper.createElement("hibernate-mapping");
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
