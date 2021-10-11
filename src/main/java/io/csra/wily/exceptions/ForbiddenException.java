package io.csra.wily.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = -6574061381597432062L;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String s) {
        super(s);
    }

}
