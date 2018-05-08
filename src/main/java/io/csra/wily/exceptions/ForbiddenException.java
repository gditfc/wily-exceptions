package io.csra.wily.exceptions;


/**
 * ForbiddenException
 * 		Will return 403.
 * 
 * @author MikeR 02/27/2015 Sprint 16 created.
 *
 */
public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = -6574061381597432062L;

	public ForbiddenException() {
		super();
	}

	public ForbiddenException(String s) {
		super(s);
	}

}
