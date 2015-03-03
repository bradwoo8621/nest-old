/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.persistent.CollectionType;

/**
 * set collection parameter
 * 
 * @author brad.wu
 */
public class SetCollectionParameter extends AbstractCollectionParameter {
	private static final long serialVersionUID = -4452138323956925429L;

	public SetCollectionParameter() {
		super(CollectionType.SET);
	}
}
