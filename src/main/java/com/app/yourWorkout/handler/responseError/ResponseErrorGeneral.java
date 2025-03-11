package com.app.yourWorkout.handler.responseError;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseErrorGeneral extends ResponseError{

    @JsonProperty("errors") private List<String> errors = new ArrayList<>();

    public ResponseErrorGeneral(int statusCode,
                                String errorMessage,
                                String exceptionClassName,
                                String path,
                                List<String> errors)
    {
        super(statusCode, errorMessage, exceptionClassName, path);
        this.errors.addAll(errors);
        generateTraceId(this.errors);
    }
}
