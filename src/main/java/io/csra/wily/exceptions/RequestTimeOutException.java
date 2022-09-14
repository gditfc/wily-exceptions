package io.csra.wily.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
public class RequestTimeOutException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -702716764852992892L;

    public RequestTimeOutException() {
        super();
    }

    public RequestTimeOutException(final String s) {
        super(s);
    }
}
