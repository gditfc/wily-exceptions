package io.csra.wily.exceptions;

import java.io.Serial;

public class ForbiddenException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6574061381597432062L;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(final String s) {
        super(s);
    }

}
