package com.app.yourWorkout.controller.exercise;

import com.app.yourWorkout.DTO.request.exercise.ExerciseRequest;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
@Tag(name = "Exercise", description = "exercise CRUD controllers")
public class ExerciseCreateController {
    private final ExerciseService exerciseService;

    @Operation(
            summary = "Create a new exercise",
            description = "Receives an exercise request DTO and saves a new exercise in the database"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseReadResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "Squat",
                                        "photo": "byte array data",
                                        "primaryBodyPart": {
                                            "name": "legs"
                                        },
                                        "equipment": "Barbell",
                                        "target": "Quadriceps",
                                        "instructions": "Stand with feet shoulder-width apart, lower into a squat, then return to standing.",
                                        "secondaryBodyParts": [
                                            { "name": "glutes" },
                                            { "name": "hamstrings" }
                                        ]
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorGeneral.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 400,
                                        "errorMessage": "bad request",
                                        "exceptionClassName": "org.springframework.web.bind.MethodArgumentNotValidException",
                                        "path": "",
                                        "errors": [
                                            "field name cannot be empty or blank"
                                        ],
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping("api/exercises")
    public ResponseEntity<ExerciseReadResponse> saveExercise(
            @Valid
            @RequestBody
            @Parameter(
                    required = true,
                    example = """
                                {
                                    "name": "Deadlift",
                                    "photo": "byte array data",
                                    "primaryBodyPart": {
                                        "name": "lower back"
                                    },
                                    "equipment": "Barbell",
                                    "target": "Erector Spinae",
                                    "instructions": "Stand with feet hip-width apart, bend at the hips to grip the bar, and lift by extending the hips and knees.",
                                    "secondaryBodyParts": [
                                        { "name": "glutes" },
                                        { "name": "hamstrings" }
                                    ]
                                }
                            """,
                    schema = @Schema(implementation = ExerciseRequest.class)
            )
            ExerciseRequest exerciseRequest
    ) {
        return ResponseEntity.ok(exerciseService.saveExercise(exerciseRequest));
    }
}
