package io.csra.wily.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ericl on 2/10/17.
 */

@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
public class RequestTimeOutException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -702716764852992892L;

	public RequestTimeOutException() {
        super();
    }

    public RequestTimeOutException(String s) {
        super(s);
    }
}
