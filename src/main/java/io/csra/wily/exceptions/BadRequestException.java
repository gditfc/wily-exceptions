package io.csra.wily.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -5924781531240039466L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String s) {
        super(s);
    }
}
