package io.csra.wily.exceptions;

import java.io.Serial;

public class ConflictException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -9149282336901441292L;

    public ConflictException(final String s) {
        super(s);
    }
}
