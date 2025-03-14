package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.DTO.request.exercise.ExerciseRequest;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
@Tag(name = "Exercise", description = "Exercise CRUD controllers")
public class ExerciseUpdateController {
    private final ExerciseService exerciseService;

    @Operation(
            summary = "Update an exercise by ID",
            description = "Updates an exercise in the database using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - Exercise updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseReadResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "Updated Bench Press",
                                        "photo": null,
                                        "primaryBodyPart": {
                                            "name": "Chest"
                                        },
                                        "equipment": "Barbell",
                                        "target": "Pectoralis Major",
                                        "instructions": "Updated instructions...",
                                        "secondaryBodyParts": [
                                            {
                                                "name": "Triceps"
                                            },
                                            {
                                                "name": "Shoulders"
                                            }
                                        ]
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - Invalid request body or path variable",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorGeneral.class),
                            examples = {
                                    @ExampleObject(name = "Invalid Request Body", value = """
                                            {
                                                "statusCode": 400,
                                                "errorMessage": "bad request",
                                                "exceptionClassName": "org.springframework.web.bind.MethodArgumentNotValidException",
                                                "path": "",
                                                "exceptionMessage": "Validation failed for argument...",
                                                "timestamp": "2025-03-14T12:00:00.000Z",
                                                "errors": ["this field 'name' is required and can not be empty."]
                                            }
                                            """),
                                    @ExampleObject(name = "Invalid Path Variable", value = """
                                            {
                                                "statusCode": 400,
                                                "errorMessage": "bad request",
                                                "exceptionClassName": "org.springframework.web.method.annotation.MethodArgumentTypeMismatchException",
                                                "path": "",
                                                "exceptionMessage": "Failed to convert value of type 'java.lang.String' to required type 'int' for parameter 'exerciseId'",
                                                "timestamp": "2025-03-14T12:00:00.000Z",
                                                "errors": ["failed to convert value on pathVariable for parameter 'exerciseId'"]
                                            }
                                            """)
                            }
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
    @PutMapping("api/exercises/id/{exerciseId}")
    public ResponseEntity<ExerciseReadResponse> updateExercise(
            @Parameter(required = true, example = "1") @PathVariable int exerciseId,
            @Parameter(required = true, example = """
                    {
                        "name": "Bench Press",
                        "photo": null,
                        "primaryBodyPart": {
                            "name": "Chest"
                        },
                        "equipment": "Barbell",
                        "target": "Pectoralis Major",
                        "instructions": "Lie on a bench..."
                    }
                    """
            )
            @Valid @RequestBody ExerciseRequest exerciseRequest) {
        return ResponseEntity.ok(exerciseService.updateExercise(exerciseId, exerciseRequest));
    }
}