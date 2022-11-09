package com.person.helper;

import com.person.dto.JSONResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class ResponseHelper {

    private ResponseHelper() {
        throw new IllegalStateException("Creating instance of this class in not allowed.");
    }

    public static ResponseEntity<JSONResponse> ok(Object data) {
        JSONResponse jsonResponse = new JSONResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null, null, data);
        return ResponseEntity.ok(jsonResponse);
    }

    public static ResponseEntity<JSONResponse> created(String message, String resourceId, StringBuffer requestUri, Object data) {
        String uri = buildResourceURI(requestUri, resourceId);
        JSONResponse jsonResponse = new JSONResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), message, uri, data);
        return ResponseEntity.created(URI.create(uri)).body(jsonResponse);
    }

    public static ResponseEntity<JSONResponse> badRequest(String message) {
        JSONResponse jsonResponse = new JSONResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message, null, null);
        return ResponseEntity.badRequest().body(jsonResponse);
    }


    public static ResponseEntity<JSONResponse> unAuthorized(String message) {
        JSONResponse jsonResponse = new JSONResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), message, null, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jsonResponse);
    }

    public static ResponseEntity<JSONResponse> forbidden(String message) {
        JSONResponse jsonResponse = new JSONResponse(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), message, null, null);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(jsonResponse);
    }

    public static ResponseEntity deleted() {
        return ResponseEntity.noContent().build();
    }

    public static ResponseEntity<JSONResponse> notFound(String message) {
        JSONResponse jsonResponse = new JSONResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), message, null, null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonResponse);
    }

    public static ResponseEntity<JSONResponse> internalError(String message) {
        JSONResponse jsonResponse = new JSONResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), message, null, null);
        return ResponseEntity.internalServerError().body(jsonResponse);
    }

    private static String buildResourceURI(StringBuffer requestUri, String resourceId) {

        String uri;
        if (requestUri.charAt(requestUri.length() - 1) == '/')
            uri = requestUri.append(resourceId).toString();
        else
            uri = requestUri.append("/").append(resourceId).toString();
        return uri;
    }


}
