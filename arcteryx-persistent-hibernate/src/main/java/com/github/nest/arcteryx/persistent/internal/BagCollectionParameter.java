/**
 * 
 */
package com.github.nest.arcteryx.persistent.internal;

import com.github.nest.arcteryx.persistent.CollectionType;

/**
 * bag collection parameter
 * 
 * @author brad.wu
 */
public class BagCollectionParameter extends AbstractCollectionParameter {
	private static final long serialVersionUID = 2804288225036865157L;

	public BagCollectionParameter() {
		super(CollectionType.BAG);
	}
}
