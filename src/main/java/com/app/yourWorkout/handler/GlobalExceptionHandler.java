package com.app.yourWorkout.handler;

import com.app.yourWorkout.handler.responseError.ResponseErrorGeneral;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorGeneral> handleValidationExceptions(MethodArgumentNotValidException ex,
                                                                           ServletWebRequest servletWebRequest) 
    {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ResponseErrorGeneral(
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.name(),
                                ex.getClass().getName(),
                                servletWebRequest.getContextPath(),
                                errors
                        )
                );
    }

}
