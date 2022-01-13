package io.csra.wily.exceptions;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = -6574061381597432062L;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(final String s) {
        super(s);
    }

}
