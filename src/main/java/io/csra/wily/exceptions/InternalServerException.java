package io.csra.wily.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1624386562663405086L;

    public InternalServerException() {
        super();
    }

    public InternalServerException(final String s) {
        super(s);
    }

}
