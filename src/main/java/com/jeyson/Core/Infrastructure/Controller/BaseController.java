package com.jeyson.Core.Infrastructure.Controller;
import com.jeyson.Core.Domain.Exceptions.BusinessLogicException;
import com.jeyson.Core.Domain.Exceptions.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    public static Map<String, Object> getJsonResponse(Object result) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", result);
        response.put("statusCode", 200);
        return response;
    }

    public static Map<String, Object> getJsonResponse(Object result, int statusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", result);
        response.put("statusCode", statusCode);
        return response;
    }

    public static Map<String, Object> getJsonResponse(Exception exception, int statusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("data", exception.getMessage());
        response.put("statusCode", statusCode);
        return response;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Map<String, Object>> handleError(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(getJsonResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler({ElementNotFoundException.class})
    public ResponseEntity<Map<String, Object>> notFoundError(ElementNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(getJsonResponse(exception, HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler({ElementNotFoundException.class})
    public ResponseEntity<Map<String, Object>> badRequestError(BusinessLogicException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(getJsonResponse(exception, HttpStatus.BAD_REQUEST.value()));
    }

}