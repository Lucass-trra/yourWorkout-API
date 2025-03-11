package com.app.yourWorkout.handler;

import com.app.yourWorkout.exception.CollectionEmptyException;
import com.app.yourWorkout.exception.DataNotFoundException;
import com.app.yourWorkout.exception.DuplicateDataException;
import com.app.yourWorkout.handler.responseError.ResponseErrorBusiness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseErrorBusiness> handleDataNotFoundException(DataNotFoundException ex,
                                                                             ServletWebRequest servletWebRequest) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ResponseErrorBusiness(
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.name(),
                                ex.getClass().getName(),
                                servletWebRequest.getContextPath(),
                                ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(CollectionEmptyException.class)
    public ResponseEntity<ResponseErrorBusiness> handleCollectionEmptyException(CollectionEmptyException ex,
                                                                                ServletWebRequest servletWebRequest) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ResponseErrorBusiness(
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.name(),
                                ex.getClass().getName(),
                                servletWebRequest.getContextPath(),
                                ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ResponseErrorBusiness> handleDuplicateDataException(DuplicateDataException ex,
                                                                              ServletWebRequest servletWebRequest) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ResponseErrorBusiness(
                                HttpStatus.CONFLICT.value(),
                                HttpStatus.CONFLICT.name(),
                                ex.getClass().getName(),
                                servletWebRequest.getContextPath(),
                                ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorBusiness> handleGenericException(Exception ex,
                                                                        ServletWebRequest servletWebRequest) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ResponseErrorBusiness(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                                ex.getClass().getName(),
                                servletWebRequest.getContextPath(),
                                ex.getMessage()
                        )
                );
    }
}
