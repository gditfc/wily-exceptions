package io.csra.wily.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4663374093452578391L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(final String s) {
        super(s);
    }

}
