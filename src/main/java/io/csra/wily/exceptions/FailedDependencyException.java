package io.csra.wily.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY)
public class FailedDependencyException extends RuntimeException {

    private static final long serialVersionUID = -5438645318921642260L;

    public FailedDependencyException() {
        super();
    }

    public FailedDependencyException(final String string) {
        super(string);
    }

}
