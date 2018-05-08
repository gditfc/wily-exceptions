package io.csra.wily.exceptions.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.commons.lang3.StringUtils;
import org.dozer.MappingException;
import io.csra.wily.exceptions.*;
import io.csra.wily.exceptions.model.JsonResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 * This control advice will capture all thrown exceptions so that they are converted to proper status
 * codes with appropriate messages. Custom messages can be supplied in the Exception, if not the default
 * message for that HttpStatus will be provided back to the client.
 *
 * @author ndimola
 */
@ControllerAdvice
public class GlobalRestExceptionHandler extends DefaultHandlerExceptionResolver {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

	private static final String DEFAULT_EXCEPTION_MESSAGE = "An unexpected error has occurred. Please try again later.";
	private static final String NONCOMPLIANT_JSON = "Noncompliant JSON";

	@Autowired
	private Environment environment;

	@ExceptionHandler(value = { IllegalArgumentException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException e, WebRequest request, HttpServletResponse response) {
		return handleException(e, HttpStatus.CONFLICT, request, response);
	}

	@ExceptionHandler(value = { NotFoundException.class })
	protected ResponseEntity<Object> handleNotFound(RuntimeException e, WebRequest request, HttpServletResponse response) {
		return handleException(e, HttpStatus.NOT_FOUND, request, response);
	}

	@ExceptionHandler(value = { ForbiddenException.class })
	protected ResponseEntity<Object> handleForbidden(RuntimeException e, WebRequest request, HttpServletResponse response) {
		return handleException(e, HttpStatus.FORBIDDEN, request, response);
	}

	@ExceptionHandler(value = { BadRequestException.class })
	protected ResponseEntity<Object> handleBadRequest(RuntimeException e, WebRequest request, HttpServletResponse response) {
		return handleException(e, HttpStatus.BAD_REQUEST, request, response);
	}

	@ExceptionHandler(value = { RestClientException.class, DatatypeConfigurationException.class })
	protected ResponseEntity<Object> handleServiceUnavailableException(RuntimeException e, WebRequest request, HttpServletResponse response) {
		return handleException(e, HttpStatus.SERVICE_UNAVAILABLE, request, response);
	}

	@ExceptionHandler(value = { UnauthorizedException.class })
	protected ResponseEntity<Object> handleUnauthorizedException(RuntimeException e, WebRequest request, HttpServletResponse response) {
		return handleException(e, HttpStatus.UNAUTHORIZED, request, response);
	}

	@ExceptionHandler(value = { AccessDeniedException.class })
	protected ResponseEntity<Object> handleAccessDeniedException(RuntimeException e, WebRequest request, HttpServletResponse response) {
		return handleException(e, HttpStatus.FORBIDDEN, request, response);
	}

	@ExceptionHandler(value = {GenericRestException.class})
	protected ResponseEntity<Object> handleGenericRestException(RuntimeException e, WebRequest request, HttpServletResponse response) {
		return handleException(e, ((GenericRestException) e).getStatus(), request, response);
	}

	@ExceptionHandler(value = { InternalServerException.class, IllegalStateException.class, MappingException.class, RuntimeException.class })
	protected ResponseEntity<Object> handleAllException(RuntimeException e, WebRequest request, HttpServletResponse response) {
		return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR, request, response);
	}

	@ExceptionHandler(value = {RequestTimeOutException.class})
	public ResponseEntity<Object> handleRequestTimeOutExcpeiton(RuntimeException e, WebRequest request, HttpServletResponse response) {
		return handleException(e, HttpStatus.REQUEST_TIMEOUT, request, response);
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> processValidationError(MethodArgumentNotValidException e, WebRequest request, HttpServletResponse response) {
		BindingResult result = e.getBindingResult();
		String message = result.getFieldErrors().get(0).getDefaultMessage();

		return handleException(new RuntimeException(message, e), HttpStatus.BAD_REQUEST, request, response);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> fallbackExceptionHandler(RuntimeException e, WebRequest request, HttpServletResponse response) {
		return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR, request, response);
	}

	/**
	 * Determines if there is a Rest End-Point JSON contract breach and issues a Bad Request.
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	protected ResponseEntity<Object> handleHttpMessageNotReadableException(RuntimeException e, WebRequest request, HttpServletResponse response) {
		if (e.getCause() != null && e.getCause() instanceof JsonMappingException) {
			return handleException(new RuntimeException(NONCOMPLIANT_JSON, e), HttpStatus.UNSUPPORTED_MEDIA_TYPE, request, response);
		} else {
			return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR, request, response);
		}
	}

	/**
	 * All other exception handlers should funnel their response through this method. It will handle both the logging and
	 * return the proper response in the expected format. This will ensure consistent messages returned from all endpoints in
	 * situations where an HTTP Status of 200 is not provided.
	 * 
	 * @param e
	 * @param status
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> handleException(RuntimeException e, HttpStatus status, WebRequest request, HttpServletResponse response) {
		String message = e.getMessage() != null ? e.getMessage() : status.getReasonPhrase();

		response.reset();

		switch (status) {
			case INTERNAL_SERVER_ERROR:
				message = handleInternalServerError(e, status, request);
				break;
			case SERVICE_UNAVAILABLE:
				LOGGER.error(e.getMessage(), e);
				break;
			case UNSUPPORTED_MEDIA_TYPE:
				LOGGER.error(e.getMessage(), e);
				break;
			default:
				LOGGER.debug(e.getMessage(), e);
				break;
		}

		return new ResponseEntity<Object>(getResponseDto(message, status), status);
	}

	/**
	 * Helper method for {@link #handleException handleException}. This method deals with Internal Server Errors specifically
	 * in order to make the code more testable, readable, and maintainable.
	 * 
	 * @param e
	 * @param status
	 * @param request
	 * @return
	 */
	private String handleInternalServerError(RuntimeException e, HttpStatus status, WebRequest request) {
		LOGGER.error(e.getMessage(), e);
		request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);

		String overrideMessage = environment.getProperty("exception.message.default");
		if (StringUtils.isNotBlank(overrideMessage)) {
			return overrideMessage;
		}

		return DEFAULT_EXCEPTION_MESSAGE;
	}

	/**
	 * Helper method to create response DTO.
	 * 
	 * @param message
	 * @return
	 */
	private JsonResponseDto getResponseDto(String message, HttpStatus status) {
		JsonResponseDto dto = new JsonResponseDto();
		dto.setError(status.getReasonPhrase());
		dto.setStatus(status.value());
		dto.setMessage(message);
		return dto;
	}
}
