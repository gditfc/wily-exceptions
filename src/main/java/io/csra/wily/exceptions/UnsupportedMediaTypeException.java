package io.csra.wily.exceptions;

import java.io.Serial;

public class UnsupportedMediaTypeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5924781531246969466L;

    public UnsupportedMediaTypeException() {
        super();
    }

    public UnsupportedMediaTypeException(final String s) {
        super(s);
    }
}
