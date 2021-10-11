package io.csra.wily.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class UnsupportedMediaTypeException extends RuntimeException {

    private static final long serialVersionUID = -5924781531246969466L;

    public UnsupportedMediaTypeException() {
        super();
    }

    public UnsupportedMediaTypeException(String s) {
        super(s);
    }
}
