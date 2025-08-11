package org.example.hw.advice;

import org.example.hw.dto.ErrorDetails;
import org.example.hw.exception.BookNotFoundException;
import org.example.hw.exception.DuplicateBookException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFound(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails(ex.getMessage()));
    }

    @ExceptionHandler(DuplicateBookException.class)
    public ResponseEntity<ErrorDetails> handleDuplicate(DuplicateBookException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorDetails(ex.getMessage()));
    }
}
