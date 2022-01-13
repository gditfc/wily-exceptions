package io.csra.wily.exceptions.controller;

import io.csra.wily.exceptions.model.JsonResponseDTO;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Global Error Controller that ensures all exceptions are converted to json before going back
 * to the client. Uses the JsonResponseDto as the format for the response.
 *
 * @author ndimola
 */
@RestController
public class GlobalRestErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public GlobalRestErrorController(final ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("${server.error.path:/error}")
    public JsonResponseDTO error(final HttpServletRequest request, final WebRequest webRequest, final HttpServletResponse response) {
        final Map<String, Object> attrs = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());

        final JsonResponseDTO dto = new JsonResponseDTO();
        dto.setStatus(response.getStatus());
        dto.setError((String) attrs.get("error"));
        dto.setMessage((String) attrs.get("message"));
        dto.setTimestamp((Date) attrs.get("timestamp"));

        return dto;
    }

}
