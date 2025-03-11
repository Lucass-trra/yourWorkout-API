package com.app.yourWorkout.handler.responseError;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseErrorBusiness extends ResponseError{
    @JsonProperty private String exceptionMessage;

    public ResponseErrorBusiness(
            int statusCode,
            String errorMessage,
            String exceptionClassName,
            String path,
            String exceptionMessage
    ) {
        super(statusCode, errorMessage, exceptionClassName, path);
        generateTraceId(List.of( this.exceptionMessage = exceptionMessage ));
    }
}
