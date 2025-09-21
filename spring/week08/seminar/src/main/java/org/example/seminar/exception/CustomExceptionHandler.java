package org.example.seminar.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);


    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Map<String, String>> handleException(CustomException e, HttpServletRequest request) {
        LOGGER.error("Advice 내 handleException 호출, URI: {}, 메시지: {}",
                request.getRequestURI(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatus().getMessage());
        map.put("code", Integer.toString(e.getHttpStatus().getCode()));
        map.put("message", e.getMessage());

        return ResponseEntity
                .status(e.getHttpStatus().getCode())
                .body(map);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        LOGGER.error("Advice 내 handleMethodArgumentNotValidException 호출, URI: {}", request.getRequestURI());

        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Validation failed";

        Map<String, String> map = new HashMap<>();
        map.put("error type", HttpStatus.BAD_REQUEST.getMessage());
        map.put("code", Integer.toString(HttpStatus.BAD_REQUEST.getCode()));
        map.put("message", errorMessage);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.getCode())
                .body(map);
    }


}