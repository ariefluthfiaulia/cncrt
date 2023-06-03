package com.ticket.app.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    private static final String RESPONSE_API_STATUS_CODE = "statusCode";
    private static final String RESPONSE_API_STATUS_MESSAGE = "statusMessage";
    private static final String RESPONSE_API_BODY = "body";
    private static final String RESPONSE_API_ERROR = "errors";

    private static ResponseEntity<Map<String, Object>> getMapResponseEntity(HttpStatus status, Object body, String responseApiBody) {
        Map<String, Object> responses = new HashMap<>();
        responses.put(RESPONSE_API_STATUS_CODE, status.value());
        responses.put(RESPONSE_API_STATUS_MESSAGE, status.getReasonPhrase());
        responses.put(responseApiBody, body);
        return ResponseEntity.status(status.value()).body(responses);
    }

    public static ResponseEntity<Map<String, Object>> returnResponse(HttpStatus status, Object body) {
        return getMapResponseEntity(status, body, RESPONSE_API_BODY);
    }

    public static ResponseEntity<Map<String, Object>> returnErrorResponse(HttpStatus status, Object body) {
        return getMapResponseEntity(status, body, RESPONSE_API_ERROR);
    }
}
