package io.csra.wily.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4663374093452578391L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String s) {
        super(s);
    }

}
