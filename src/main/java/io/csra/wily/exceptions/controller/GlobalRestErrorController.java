package io.csra.wily.exceptions.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.csra.wily.exceptions.model.JsonResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * Global Error Controller that ensures all exceptions are converted to json before going back
 * to the client. Uses the JsonResponseDto as the format for the response.
 *
 * @author ndimola
 */
@RestController
public class GlobalRestErrorController implements ErrorController {

	private static final String PATH = "/error";

	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping(value = PATH)
	public JsonResponseDto error(HttpServletRequest request, WebRequest webRequest, HttpServletResponse response) {
		Map<String, Object> attrs = getErrorAttributes(webRequest, false);

		JsonResponseDto dto = new JsonResponseDto();
		dto.setStatus(response.getStatus());
		dto.setError((String) attrs.get("error"));
		dto.setMessage((String) attrs.get("message"));
		dto.setTimestamp((Date) attrs.get("timestamp"));

		return dto;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

	private Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {
		return errorAttributes.getErrorAttributes(request, includeStackTrace);
	}
}