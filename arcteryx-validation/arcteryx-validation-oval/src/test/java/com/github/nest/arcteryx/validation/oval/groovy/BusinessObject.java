/**
 * 
 */
package com.github.nest.arcteryx.validation.oval.groovy;

import net.sf.oval.constraint.Assert;
import net.sf.oval.constraint.NotNull;

/**
 * @author brad.wu
 */
public class BusinessObject {
	@NotNull
	public String deliveryAddress;

	@NotNull
	public String invoiceAddress;

	// mailingAddress must either be the delivery address or the invoice address
	@Assert(expr = "_value ==_this.deliveryAddress || _value == _this.invoiceAddress", lang = "groovy")
	public String mailingAddress;
}
