package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.handler.responseError.ResponseErrorBusiness;
import com.app.yourWorkout.handler.responseError.ResponseErrorGeneral;
import com.app.yourWorkout.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/exercises")
@AllArgsConstructor
@Tag(name = "Exercise", description = "exercise CRUD controllers")
public class ExerciseDeleteController {
    private final ExerciseService exerciseService;

    @Operation(
            summary = "Delete an exercise by ID",
            description = "Deletes an exercise from the database using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content - Exercise successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - Invalid path variable",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorGeneral.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 400,
                                        "errorMessage": "bad request",
                                        "exceptionClassName": "org.springframework.web.method.annotation.MethodArgumentTypeMismatchException",
                                        "path": "",
                                        "exceptionMessage": "Failed to convert value of type 'java.lang.String' to required type 'int' for parameter 'exerciseId'",
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """ )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - Exercise with the given ID does not exist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 404,
                                        "errorMessage": "not found",
                                        "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                        "path": "",
                                        "exceptionMessage": "Exercise with ID 10 was not found",
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """ )
                    )
            )
    })
    @DeleteMapping("id/{exerciseId}")
    public ResponseEntity<Void> deleteById(
            @PathVariable
            @Parameter(required = true, example = "20")
            int exerciseId) {
        exerciseService.deleteById(exerciseId);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete an exercise by name",
            description = "Deletes an exercise from the database using its unique name."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No Content - Exercise successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - Invalid path variable",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorGeneral.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 400,
                                        "errorMessage": "bad request",
                                        "exceptionClassName": "org.springframework.web.method.annotation.MethodArgumentTypeMismatchException",
                                        "path": "",
                                        "exceptionMessage": "Failed to convert value of type 'java.lang.Integer' to required type 'java.lang.String' for parameter 'name'",
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """ )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - Exercise with the given name does not exist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 404,
                                        "errorMessage": "not found",
                                        "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                        "path": "",
                                        "exceptionMessage": "Exercise with name 'Deadlift' was not found",
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """ )
                    )
            )
    })
    @DeleteMapping("name/{name}")
    public ResponseEntity<Void> deleteByName(
            @PathVariable
            @Parameter(required = true, example = "lower legs")
            String name) {
        exerciseService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
}
