package io.csra.wily.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4663374093452578391L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(final String s) {
        super(s);
    }

}
