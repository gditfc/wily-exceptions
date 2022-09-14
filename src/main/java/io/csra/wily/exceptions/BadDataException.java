package io.csra.wily.exceptions;

import java.io.Serial;

public class BadDataException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5924781531241139466L;

    public BadDataException() {
        super();
    }

    public BadDataException(final String s) {
        super(s);
    }
}

