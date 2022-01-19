package io.csra.wily.exceptions;

import java.io.Serial;

public class FailedDependencyException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7898483279395577092L;

    public FailedDependencyException() {
        super();
    }

    public FailedDependencyException(final String s) {
        super(s);
    }
}
