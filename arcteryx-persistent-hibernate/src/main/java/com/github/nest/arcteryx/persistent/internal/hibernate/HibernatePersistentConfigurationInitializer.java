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

import org.apache.commons.lang3.ArrayUtils;
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
import com.github.nest.arcteryx.meta.beans.IBeanDescriptor;
import com.github.nest.arcteryx.persistent.CascadeType;
import com.github.nest.arcteryx.persistent.CollectionType;
import com.github.nest.arcteryx.persistent.ICollectionParameter;
import com.github.nest.arcteryx.persistent.IEmbeddedPersistentColumn;
import com.github.nest.arcteryx.persistent.IManyToManyPersistentColumn;
import com.github.nest.arcteryx.persistent.IManyToOnePersistentColumn;
import com.github.nest.arcteryx.persistent.IOneToManyPersistentColumn;
import com.github.nest.arcteryx.persistent.IOneToManyReversePersistentColumn;
import com.github.nest.arcteryx.persistent.IOneToOnePersistentColumn;
import com.github.nest.arcteryx.persistent.IOneToOneReversePersistentColumn;
import com.github.nest.arcteryx.persistent.IPersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentBeanPropertyDescriptor;
import com.github.nest.arcteryx.persistent.IPersistentColumn;
import com.github.nest.arcteryx.persistent.IPersistentConfiguration;
import com.github.nest.arcteryx.persistent.IPersistentConfigurationInitializer;
import com.github.nest.arcteryx.persistent.IPrimaryKey;
import com.github.nest.arcteryx.persistent.IPrimitivePersistentColumn;
import com.github.nest.arcteryx.persistent.IStandalonePersistentBeanDescriptor;
import com.github.nest.arcteryx.persistent.PrimitiveColumnType;
import com.github.nest.arcteryx.persistent.internal.IdBagCollectionParameter;
import com.github.nest.arcteryx.persistent.internal.ListCollectionParameter;
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
	/**
	 * property comparator. in hibernate, primary key column must be the first
	 * element of class. the comparator will sort the persistent property by
	 * following order:
	 * <ol>
	 * <li>primary key property</li>
	 * <li>version property</li>
	 * <li>other properties</li>
	 * </ol>
	 * 
	 * @author brad.wu
	 */
	private final class PropertyComparator implements Comparator<IPersistentBeanPropertyDescriptor> {
		private IPersistentBeanDescriptor descriptor = null;

		public PropertyComparator(IPersistentBeanDescriptor descriptor) {
			this.descriptor = descriptor;
		}

		/**
		 * @return the descriptor
		 */
		public IPersistentBeanDescriptor getBeanDescriptor() {
			return descriptor;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(IPersistentBeanPropertyDescriptor o1, IPersistentBeanPropertyDescriptor o2) {
			int metrics1 = getValueOfProperty(o1);
			int metrics2 = getValueOfProperty(o2);
			if (metrics1 != metrics2) {
				return metrics1 - metrics2;
			} else {
				IPersistentBeanDescriptor beanDescriptor = this.getBeanDescriptor();
				if (beanDescriptor instanceof IStandalonePersistentBeanDescriptor) {
					IStandalonePersistentBeanDescriptor bean = (IStandalonePersistentBeanDescriptor) beanDescriptor;
					String joinedTable1 = bean.getJoinedTableName(o1.getName());
					String joinedTable2 = bean.getJoinedTableName(o2.getName());
					if (StringUtils.isBlank(joinedTable1)) {
						return StringUtils.isBlank(joinedTable2) ? 0 : -1;
					} else if (StringUtils.isBlank(joinedTable2)) {
						return 1;
					} else {
						return joinedTable1.compareTo(joinedTable2);
					}
				} else {
					// embedded has no sort
					return 0;
				}
			}
		}

		private int getValueOfProperty(IPersistentBeanPropertyDescriptor descriptor) {
			IPersistentColumn column = descriptor.getPersistentColumn();
			if (column instanceof IPrimitivePersistentColumn) {
				IPrimitivePersistentColumn ippc = (IPrimitivePersistentColumn) column;
				if (ippc.isPrimaryKey()) {
					return 1;
				} else if (ippc.isVersion()) {
					return 2;
				}
			}
			return 3;
		}
	}

	private final class EmbeddedStack {
		private List<String> propertyNameList = new LinkedList<String>();
		private List<IEmbeddedPersistentColumn> embeddedColumnList = new LinkedList<IEmbeddedPersistentColumn>();

		/**
		 * add embedded column info.
		 * 
		 * @param propertyName
		 * @param embedded
		 */
		public void add(String propertyName, IEmbeddedPersistentColumn embedded) {
			propertyNameList.add(0, propertyName);
			embeddedColumnList.add(0, embedded);
		}

		/**
		 * get column name.<br>
		 * column name might be overridden by bean which it is embedded. and
		 * might be overridden multiple times. use the property name to find it
		 * is overridden, or return original column name if not overridden.
		 * 
		 * @param propertyName
		 * @param columnName
		 * @return
		 */
		public String getColumnName(String propertyName, String columnName) {
			String key = propertyName;
			String overriddenColumnName = columnName;
			String temp = null;
			for (int index = 0, count = propertyNameList.size(); index < count; index++) {
				String parentPropertyName = propertyNameList.get(index);
				IEmbeddedPersistentColumn parentEmbeddedColumn = embeddedColumnList.get(index);
				temp = parentEmbeddedColumn.getOverriddenColumnName(key);
				if (!StringUtils.isBlank(temp)) {
					// column name is overridden
					overriddenColumnName = temp;
				}
				// trace to higher level
				key = parentPropertyName + "." + key;
			}
			return overriddenColumnName;
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
		Element classElement = null;

		IStandalonePersistentBeanDescriptor extendsFromBean = descriptor.getExtendsFrom();

		if (extendsFromBean != null) {
			classElement = createSubClassElement(descriptor, extendsFromBean);
		} else {
			classElement = DocumentHelper.createElement("class");
			classElement.addAttribute("name", descriptor.getBeanClass().getSimpleName());
			classElement.addAttribute("table", descriptor.getTableName());

			Element discriminatorElement = null;
			if (StringUtils.isNoneBlank(descriptor.getDiscriminatorColumnName())) {
				discriminatorElement = DocumentHelper.createElement("discriminator");
				discriminatorElement.addAttribute("column", descriptor.getDiscriminatorColumnName());
			}

			List<Element> propertyElements = createPropertyElements(descriptor, null);
			for (Element propertyElement : propertyElements) {
				classElement.add(propertyElement);
				// discriminator element must be after id and before others
				if (discriminatorElement != null && propertyElement.getName().equals("id")) {
					classElement.add(discriminatorElement);
				}
			}
		}
		return classElement;
	}

	protected Element createSubClassElement(IStandalonePersistentBeanDescriptor descriptor,
			IStandalonePersistentBeanDescriptor extendsFromBean) {
		Element classElement;
		classElement = DocumentHelper.createElement("subclass");
		classElement.addAttribute("name", descriptor.getBeanClass().getSimpleName());
		classElement.addAttribute("extends", extendsFromBean.getBeanClass().getName());
		classElement.addAttribute("discriminator-value", descriptor.getDiscriminatorValue());

		Element parentElement = null;
		if (StringUtils.isNoneBlank(descriptor.getTableName())) {
			// the extend class in persistent in another table
			Element joinElement = DocumentHelper.createElement("join");
			joinElement.addAttribute("table", descriptor.getTableName());
			classElement.add(joinElement);

			Element keyElement = DocumentHelper.createElement("key");
			keyElement.addAttribute("column", descriptor.getForeignKeyColumnName());
			joinElement.add(keyElement);

			parentElement = joinElement;
		} else {
			// share the table with super class
			parentElement = classElement;
		}

		List<Element> propertyElements = createPropertyElements(descriptor, null);
		for (Element propertyElement : propertyElements) {
			parentElement.add(propertyElement);
		}
		return classElement;
	}

	/**
	 * create property elements
	 * 
	 * @param descriptor
	 * @param embeddedStack
	 * @return
	 */
	protected List<Element> createPropertyElements(IPersistentBeanDescriptor descriptor, EmbeddedStack embeddedStack) {
		Collection<IPersistentBeanPropertyDescriptor> properties = descriptor.getPersistentProperties();
		List<IPersistentBeanPropertyDescriptor> list = new ArrayList<IPersistentBeanPropertyDescriptor>(
				properties.size());
		list.addAll(properties);
		// filter properties if bean is subclass
		if (descriptor instanceof IStandalonePersistentBeanDescriptor) {
			IStandalonePersistentBeanDescriptor bean = (IStandalonePersistentBeanDescriptor) descriptor;
			IStandalonePersistentBeanDescriptor extendsFromBean = bean.getExtendsFrom();
			if (extendsFromBean != null) {
				// current is subclass
				Class<?> extendsFromBeanClass = extendsFromBean.getBeanClass();
				// remove properties from extends from bean and its ancestors
				for (int index = list.size() - 1; index >= 0; index--) {
					IPersistentBeanPropertyDescriptor property = list.get(index);
					Class<?> beanClass = property.getBeanDescriptor().getBeanClass();
					if (beanClass.isAssignableFrom(extendsFromBeanClass)) {
						list.remove(index);
					}
				}
			}
		}
		// id first
		Collections.sort(list, new PropertyComparator(descriptor));

		List<Element> propertyElements = new LinkedList<Element>();

		// for stand-alone beans, more than one tables might be existed.
		String currentJoinedTableName = null;
		IStandalonePersistentBeanDescriptor persistentDescriptor = null;
		if (descriptor instanceof IStandalonePersistentBeanDescriptor) {
			persistentDescriptor = (IStandalonePersistentBeanDescriptor) descriptor;
		}
		Element joinedTableElement = null;
		for (IPersistentBeanPropertyDescriptor property : list) {
			if (persistentDescriptor != null && persistentDescriptor.isJoined(property.getName())) {
				String joinedTableName = persistentDescriptor.getJoinedTableName(property.getName());
				if (!joinedTableName.equals(currentJoinedTableName)) {
					joinedTableElement = DocumentHelper.createElement("join");
					joinedTableElement.addAttribute("table", joinedTableName);
					Element keyElement = DocumentHelper.createElement("key");
					keyElement.addAttribute("column",
							persistentDescriptor.getJoinedTablePrimaryKeyColumnName(joinedTableName));
					joinedTableElement.add(keyElement);
					currentJoinedTableName = joinedTableName;
				}
			}
			if (joinedTableElement != null) {
				if (!propertyElements.contains(joinedTableElement)) {
					propertyElements.add(joinedTableElement);
				}
				joinedTableElement.add(createPropertyElement(property, embeddedStack));
			} else {
				propertyElements.add(createPropertyElement(property, embeddedStack));
			}
		}
		return propertyElements;
	}

	/**
	 * create property element
	 * 
	 * @param property
	 * @param embeddedStack
	 * @return
	 */
	protected Element createPropertyElement(IPersistentBeanPropertyDescriptor property, EmbeddedStack embeddedStack) {
		IPersistentColumn column = property.getPersistentColumn();

		if (column instanceof IPrimitivePersistentColumn) {
			return createPrimitiveColumnElement(property, (IPrimitivePersistentColumn) column, embeddedStack);
		} else if (column instanceof IEmbeddedPersistentColumn) {
			return createEmbeddedColumnElement(property, (IEmbeddedPersistentColumn) column, embeddedStack);
		} else if (column instanceof IManyToOnePersistentColumn) {
			return createManyToOneColumnElement(property, (IManyToOnePersistentColumn) column);
		} else if (column instanceof IOneToOnePersistentColumn) {
			return createOneToOneColumnElement(property, (IOneToOnePersistentColumn) column);
		} else if (column instanceof IOneToOneReversePersistentColumn) {
			return createOneToOneColumnElement(property, (IOneToOneReversePersistentColumn) column);
		} else if (column instanceof IOneToManyPersistentColumn) {
			return createOneToManyColumnElement(property, (IOneToManyPersistentColumn) column);
		} else if (column instanceof IOneToManyReversePersistentColumn) {
			return createOneToManyColumnElement(property, (IOneToManyReversePersistentColumn) column);
		} else if (column instanceof IManyToManyPersistentColumn) {
			return createManyToManyColumnElement(property, (IManyToManyPersistentColumn) column);
		} else {
			throw new ResourceException("Property [" + property.getResourceDescriptor().getResourceClass().getName()
					+ "#" + property.getName() + "] cannot be convert to hibernate xml.");
		}
	}

	/**
	 * create many-to-many property element
	 * 
	 * @param property
	 * @param column
	 * @return
	 */
	protected Element createManyToManyColumnElement(IPersistentBeanPropertyDescriptor property,
			IManyToManyPersistentColumn column) {
		IBeanDescriptor referredBean = column.getReferencedBean();
		if (column.isInSameContext() && (referredBean instanceof IPersistentBeanDescriptor)) {
			return createManyToManyColumnElementAllPersistent(property, column);
		} else {
			return createManyToManyColumnElementNotPersistent(property, column);
		}
	}

	/**
	 * create many-to-many property element, if the referenced bean is not in
	 * same context with current bean or the referenced bean is not persistent.
	 * 
	 * @param property
	 * @param column
	 * @return
	 */
	protected Element createManyToManyColumnElementNotPersistent(IPersistentBeanPropertyDescriptor property,
			IManyToManyPersistentColumn column) {
		Element manyToManyElement = internalCreateManyToManyColumnElement(property, column);

		Element elementElement = DocumentHelper.createElement("composite-element");
		elementElement.addAttribute("class", column.getReferencedBean().getBeanClass().getName());
		Element propertyElement = DocumentHelper.createElement("property");
		propertyElement.addAttribute("column", column.getForeignKeyColumnNameToRefer());
		propertyElement.addAttribute("name", column.getForeignKeyPropertyNameToRefer());
		elementElement.add(propertyElement);
		manyToManyElement.add(elementElement);

		return manyToManyElement;
	}

	/**
	 * create many-to-many property element, if the beans in both sides are
	 * persistent in same context.
	 * 
	 * @param property
	 * @param column
	 * @return
	 */
	protected Element createManyToManyColumnElementAllPersistent(IPersistentBeanPropertyDescriptor property,
			IManyToManyPersistentColumn column) {
		Element manyToManyElement = internalCreateManyToManyColumnElement(property, column);

		Element linkElement = DocumentHelper.createElement("many-to-many");
		linkElement.addAttribute("class", column.getReferencedBean().getBeanClass().getName());
		linkElement.addAttribute("column", column.getForeignKeyColumnNameToRefer());
		manyToManyElement.add(linkElement);

		return manyToManyElement;
	}

	/**
	 * create many-to-many property element.
	 * 
	 * @param property
	 * @param column
	 * @return
	 */
	protected Element internalCreateManyToManyColumnElement(IPersistentBeanPropertyDescriptor property,
			IManyToManyPersistentColumn column) {
		Element element = null;
		ICollectionParameter parameter = column.getCollectionParameter();
		if (CollectionType.BAG == parameter.getType()) {
			element = DocumentHelper.createElement("bag");
		} else if (CollectionType.SET == parameter.getType()) {
			element = DocumentHelper.createElement("set");
		} else if (CollectionType.LIST == parameter.getType()) {
			element = DocumentHelper.createElement("list");
		} else if (CollectionType.IDBAG == parameter.getType()) {
			element = DocumentHelper.createElement("idbag");
			// collection-id
			Element collectionIdElement = DocumentHelper.createElement("collection-id");
			collectionIdElement.addAttribute("column",
					(String) parameter.getParameter(IdBagCollectionParameter.COLLECTION_ID_COLUMN_NAME));
			collectionIdElement.add(createPrimaryKeyGeneratorElement((IPrimaryKey) parameter
					.getParameter(IdBagCollectionParameter.COLLECTION_ID_GENERATOR)));
			// column type
			collectionIdElement.addAttribute("type", generateTypeString((PrimitiveColumnType) parameter
					.getParameter(IdBagCollectionParameter.COLLECTION_ID_COLUMN_TYPE)));
			element.add(collectionIdElement);
		}

		element.addAttribute("name", property.getName());
		CascadeType[] cascadeTypes = parameter.getCascadeTypes();
		element.addAttribute("cascade", generateCascadeAttributeValue(cascadeTypes));

		Element keyElement = DocumentHelper.createElement("key");
		keyElement.addAttribute("column", column.getForeignKeyColumnNameToMe());
		element.add(keyElement);

		if (CollectionType.LIST == parameter.getType()) {
			Element listIndexElement = DocumentHelper.createElement("list-index");
			listIndexElement.addAttribute("column",
					(String) parameter.getParameter(ListCollectionParameter.LIST_INDEX_COLUMN_NAME));
			element.add(listIndexElement);
		}

		element.addAttribute("table", column.getIntermediateTableName());
		return element;
	}

	/**
	 * create one-to-many property element, for reverse parent property
	 * 
	 * @param property
	 * @param column
	 * @return
	 */
	protected Element createOneToManyColumnElement(IPersistentBeanPropertyDescriptor property,
			IOneToManyReversePersistentColumn column) {
		Element oneToManyElement = DocumentHelper.createElement("many-to-one");
		oneToManyElement.addAttribute("name", property.getName());
		// add fetch attributes automatically
		oneToManyElement.addAttribute("fetch", "select");

		Element columnElement = DocumentHelper.createElement("column");
		IPersistentBeanDescriptor parentBeanDescriptor = column.getParentBean();
		Collection<IPersistentBeanPropertyDescriptor> parentProperties = parentBeanDescriptor.getPersistentProperties();
		for (IPersistentBeanPropertyDescriptor parentProperty : parentProperties) {
			IPersistentColumn parentColumn = parentProperty.getPersistentColumn();
			if (parentColumn instanceof IPrimitivePersistentColumn) {
				if (((IPrimitivePersistentColumn) parentColumn).isPrimaryKey()) {
					columnElement.addAttribute("name", ((IPrimitivePersistentColumn) parentColumn).getName());
					break;
				}
			}
		}
		columnElement.addAttribute("not-null", "true");
		oneToManyElement.add(columnElement);

		return oneToManyElement;
	}

	/**
	 * create one-to-many property element
	 * 
	 * @param property
	 * @param column
	 * @return
	 */
	protected Element createOneToManyColumnElement(IPersistentBeanPropertyDescriptor property,
			IOneToManyPersistentColumn column) {
		Element oneToManyElement = null;
		ICollectionParameter parameter = column.getCollectionParameter();
		if (CollectionType.BAG == parameter.getType()) {
			oneToManyElement = DocumentHelper.createElement("bag");
		} else if (CollectionType.SET == parameter.getType()) {
			oneToManyElement = DocumentHelper.createElement("set");
			// order-by attr
		} else if (CollectionType.LIST == parameter.getType()) {
			oneToManyElement = DocumentHelper.createElement("list");
			// list-index
		} else if (CollectionType.IDBAG == parameter.getType()) {
			throw new ResourceException("Idbag cannot be defined in one-to-many relationship at ["
					+ property.getBeanDescriptor().getBeanClass() + "#" + property.getName() + "].");
		}

		oneToManyElement.addAttribute("name", property.getName());
		CascadeType[] cascadeTypes = parameter.getCascadeTypes();
		oneToManyElement.addAttribute("cascade", generateCascadeAttributeValue(cascadeTypes));

		Element keyElement = DocumentHelper.createElement("key");
		keyElement.addAttribute("column", column.getForeignKeyColumnName());
		// add not-null attribute automatically
		keyElement.addAttribute("not-null", "true");
		oneToManyElement.add(keyElement);

		if (CollectionType.LIST == parameter.getType()) {
			Element listIndexElement = DocumentHelper.createElement("list-index");
			listIndexElement.addAttribute("column",
					(String) parameter.getParameter(ListCollectionParameter.LIST_INDEX_COLUMN_NAME));
			oneToManyElement.add(listIndexElement);
		}

		// add fetch attributes automatically
		oneToManyElement.addAttribute("fetch", "select");
		oneToManyElement.addAttribute("inverse", parameter.isInverse() ? "true" : "false");

		Element linkElement = DocumentHelper.createElement("one-to-many");
		linkElement.addAttribute("class", column.getSubordinateBean().getBeanClass().getName());
		oneToManyElement.add(linkElement);

		return oneToManyElement;
	}

	/**
	 * create one-to-one property element, for reverse parent property
	 * 
	 * @param property
	 * @param column
	 * @return
	 */
	protected Element createOneToOneColumnElement(IPersistentBeanPropertyDescriptor property,
			IOneToOneReversePersistentColumn column) {
		Element oneToOneElement = DocumentHelper.createElement("one-to-one");
		oneToOneElement.addAttribute("name", property.getName());
		oneToOneElement.addAttribute("class", column.getParentBean().getBeanClass().getName());
		return oneToOneElement;
	}

	/**
	 * create one to one property element
	 * 
	 * @param property
	 * @param column
	 * @return
	 */
	protected Element createOneToOneColumnElement(IPersistentBeanPropertyDescriptor property,
			IOneToOnePersistentColumn column) {
		Element oneToOneElement = DocumentHelper.createElement("one-to-one");
		oneToOneElement.addAttribute("name", property.getName());
		oneToOneElement.addAttribute("class", column.getSubordinateBean().getBeanClass().getName());
		CascadeType[] cascadeTypes = column.getCascadeTypes();
		oneToOneElement.addAttribute("cascade", generateCascadeAttributeValue(cascadeTypes));
		return oneToOneElement;
	}

	/**
	 * generate cascade attribute value
	 * 
	 * @param cascadeTypes
	 * @return
	 */
	protected String generateCascadeAttributeValue(CascadeType[] cascadeTypes) {
		if (ArrayUtils.isEmpty(cascadeTypes)) {
			cascadeTypes = IOneToOnePersistentColumn.DEFAULT_CASCADE_TYPES;
		}
		StringBuilder cascadeValues = new StringBuilder();
		for (CascadeType cascadeType : cascadeTypes) {
			if (cascadeValues.length() != 0) {
				cascadeValues.append(',');
			}
			cascadeValues.append(cascadeType.getName());
		}
		return cascadeValues.toString();
	}

	/**
	 * create many to one property element
	 * 
	 * @param property
	 * @param column
	 * @return
	 */
	protected Element createManyToOneColumnElement(IPersistentBeanPropertyDescriptor property,
			IManyToOnePersistentColumn column) {
		Element manyToOneElement = null;
		IBeanDescriptor referredBean = column.getReferencedBean();
		if (column.isInSameContext() && (referredBean instanceof IPersistentBeanDescriptor)) {
			// only when the referenced bean is persistent and in same context,
			// treated as many-to-one relationship
			manyToOneElement = DocumentHelper.createElement("many-to-one");
			manyToOneElement.addAttribute("column", column.getForeignKeyColumnName());
		} else {
			// other type beans, treated as embedded bean
			manyToOneElement = DocumentHelper.createElement("component");
			Element propertyElement = DocumentHelper.createElement("property");
			propertyElement.addAttribute("name", column.getForeignKeyPropertyName());
			propertyElement.addAttribute("column", column.getForeignKeyColumnName());
			manyToOneElement.add(propertyElement);
		}
		manyToOneElement.addAttribute("name", property.getName());
		manyToOneElement.addAttribute("class", referredBean.getBeanClass().getName());
		return manyToOneElement;
	}

	/**
	 * create embedded property element
	 * 
	 * @param property
	 * @param embeddedColumn
	 * @param embeddedStack
	 * @return
	 */
	protected Element createEmbeddedColumnElement(IPersistentBeanPropertyDescriptor property,
			IEmbeddedPersistentColumn embeddedColumn, EmbeddedStack embeddedStack) {
		Element componentElement = DocumentHelper.createElement("component");
		// property name
		componentElement.addAttribute("name", property.getName());

		IPersistentBeanDescriptor embeddedBean = embeddedColumn.getEmbeddedBean();
		componentElement.addAttribute("class", embeddedBean.getBeanClass().getName());
		// construct embedded stack
		if (embeddedStack == null) {
			embeddedStack = new EmbeddedStack();
		}
		embeddedStack.add(property.getName(), embeddedColumn);
		List<Element> propertyElements = createPropertyElements(embeddedBean, embeddedStack);
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
	 * @param embeddedStack
	 * @return
	 */
	protected Element createPrimitiveColumnElement(IPersistentBeanPropertyDescriptor property,
			IPrimitivePersistentColumn primitiveColumn, EmbeddedStack embeddedStack) {
		// primitive column
		Element propertyElement = null;
		if (primitiveColumn.isPrimaryKey()) {
			// primary key column
			propertyElement = DocumentHelper.createElement("id");
			propertyElement.add(createPrimaryKeyGeneratorElement(primitiveColumn.getPrimaryKeyGenerator()));
			// column type
			propertyElement.addAttribute("type", generateTypeString(primitiveColumn.getType()));
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
				propertyElement.addAttribute("type", generateTypeString(primitiveColumn.getType()));
			}
		} else {
			// normal primitive column
			propertyElement = DocumentHelper.createElement("property");
			// column type
			propertyElement.addAttribute("type", generateTypeString(primitiveColumn.getType()));
		}
		// column name
		propertyElement.addAttribute(
				"column",
				embeddedStack == null ? primitiveColumn.getName() : embeddedStack.getColumnName(property.getName(),
						primitiveColumn.getName()));
		// property name
		propertyElement.addAttribute("name", property.getName());
		return propertyElement;
	}

	/**
	 * generate type string
	 * 
	 * @param columnType
	 * @return
	 */
	protected String generateTypeString(PrimitiveColumnType columnType) {
		return this.getPrimitiveColumnTypeGenerator(columnType).generate(columnType);
	}

	/**
	 * create primary key generator element
	 * 
	 * @param generator
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Element createPrimaryKeyGeneratorElement(IPrimaryKey generator) {
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
	 * set properties
	 * 
	 * @param properties
	 */
	public void setProperties(Map<String, String> properties) {
		if (properties == null || properties.size() == 0) {
			if (this.properties != null) {
				this.properties.clear();
			}
		} else {
			if (this.properties == null) {
				synchronized (this) {
					if (this.properties == null) {
						this.properties = new Properties();
					}
				}
			}
			for (Map.Entry<String, String> entry : properties.entrySet()) {
				this.properties.put(entry.getKey(), entry.getValue());
			}
		}
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
