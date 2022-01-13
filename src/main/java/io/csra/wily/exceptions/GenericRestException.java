package io.csra.wily.exceptions;

import org.springframework.http.HttpStatus;

public class GenericRestException extends RuntimeException {

    private static final long serialVersionUID = -5924781531241139466L;

    private HttpStatus status;

    public GenericRestException(final HttpStatus status) {
        super();
        this.status = status;
    }

    public GenericRestException(final HttpStatus status, final String s) {
        super(s);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}

