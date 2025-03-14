package com.app.yourWorkout.controller.exercise;

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
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/exercises")
@AllArgsConstructor
@Tag(name = "Exercise", description = "exercise CRUD controllers")
public class ExerciseReadController {
    private final ExerciseService exerciseService;

    @Operation(
            summary = "Find an exercise by ID",
            description = "Retrieves an exercise from the database using its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - Exercise found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseReadResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "Bench Press",
                                        "photo": null,
                                        "primaryBodyPart": {
                                            "name": "Chest"
                                        },
                                        "equipment": "Barbell",
                                        "target": "Pectoralis Major",
                                        "instructions": "Lie on a bench...",
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
    @GetMapping("id/{exerciseId}")
    public ResponseEntity<ExerciseReadResponse> findById(
            @PathVariable
            @Parameter(required = true, example = "20")
            int exerciseId) {
        return ResponseEntity.ok(exerciseService.findById(exerciseId));
    }

    @Operation(
            summary = "Find an exercise by name",
            description = "Retrieves an exercise from the database using its unique name."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - Exercise found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseReadResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "Deadlift",
                                        "photo": null,
                                        "primaryBodyPart": {
                                            "name": "Back"
                                        },
                                        "equipment": "Barbell",
                                        "target": "Lower Back, Glutes, Hamstrings",
                                        "instructions": "Stand with feet...",
                                        "secondaryBodyParts": [
                                            {
                                                "name": "Forearms"
                                            }
                                        ]
                                    }
                                    """)
                    )
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
    @GetMapping("name/{name}")
    public ResponseEntity<ExerciseReadResponse> findByName(
            @PathVariable
            @Parameter(required = true, example = "upper arms")
            String name
    ) {
        return ResponseEntity.ok(exerciseService.findByName(name));
    }
}