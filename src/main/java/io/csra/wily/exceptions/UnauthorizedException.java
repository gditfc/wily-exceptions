package io.csra.wily.exceptions;

import java.io.Serial;

public class UnauthorizedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5243662440134161849L;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(final String s) {
        super(s);
    }

}
