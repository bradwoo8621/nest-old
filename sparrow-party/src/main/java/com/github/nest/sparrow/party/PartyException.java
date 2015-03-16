/**
 * 
 */
package com.github.nest.sparrow.party;

/**
 * party exception
 * 
 * @author brad.wu
 */
public class PartyException extends RuntimeException {
	private static final long serialVersionUID = -7475745579309337835L;

	public PartyException() {
		super();
	}

	public PartyException(String message, Throwable cause) {
		super(message, cause);
	}

	public PartyException(String message) {
		super(message);
	}

	public PartyException(Throwable cause) {
		super(cause);
	}
}
