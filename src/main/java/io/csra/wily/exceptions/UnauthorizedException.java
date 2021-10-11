package io.csra.wily.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = -5243662440134161849L;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String s) {
        super(s);
    }

}
