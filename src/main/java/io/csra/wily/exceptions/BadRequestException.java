package io.csra.wily.exceptions;

import java.io.Serial;

public class BadRequestException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5924781531240039466L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(final String s) {
        super(s);
    }
}
