package com.app.yourWorkout.handler.responseError;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
abstract class ResponseError{
    @JsonProperty("timestamp")
    protected ZonedDateTime timestamp = ZonedDateTime.now();

    @JsonProperty("statusCode")
    @NonNull
    protected Integer statusCode;

    @JsonProperty("errorMessage")
    @NonNull
    protected String errorMessage;

    @JsonProperty("exceptionClassName")
    @NonNull
    protected String exceptionClassName;

    @JsonProperty("path")
    @NonNull
    protected String path;

    protected static final Logger logger = LoggerFactory.getLogger(ResponseError.class);

     protected void generateTraceId(List<String> errorMessages) {
        logger.error("Trace ID: {} - Error: {}", UUID.randomUUID(), errorMessages);
    }
}
