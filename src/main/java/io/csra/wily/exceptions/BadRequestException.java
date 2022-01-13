package io.csra.wily.exceptions;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -5924781531240039466L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(final String s) {
        super(s);
    }
}
