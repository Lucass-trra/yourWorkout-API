package com.app.yourWorkout.controller.workoutExercise;

import com.app.yourWorkout.DTO.response.WorkoutExerciseReadResponse;
import com.app.yourWorkout.handler.responseError.ResponseErrorBusiness;
import com.app.yourWorkout.handler.responseError.ResponseErrorGeneral;
import com.app.yourWorkout.service.WorkoutExerciseService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "WorkoutExercise", description = "workoutExercise association CRUD controllers")
public class WorkoutExerciseReadController {
    private final WorkoutExerciseService workoutExerciseService;

    @Operation(
            summary = "Find a workout-exercise association",
            description = "Retrieves an association between a workout and an exercise."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - Workout-exercise association found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutExerciseReadResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "exercise": {
                                            "name": "Bench Press",
                                            "photo": null,
                                            "primaryBodyPart": {
                                                "name": "Chest",
                                            },
                                            "equipment": "Barbell",
                                            "target": "Pectoralis Major",
                                            "instructions": "Lie on a bench and lower the barbell to your chest.",
                                            "secondaryBodyParts": [
                                                {
                                                    "name": "Triceps",
                                                },
                                                {
                                                    "name": "Shoulders",
                                                }
                                            ]
                                        },
                                        "sets": 3,
                                        "repeats": 10,
                                        "weight": 80,
                                        "restBetweenSets": "00:01:30"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - Invalid path variables",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorGeneral.class),
                            examples = {
                                    @ExampleObject(name = "Invalid Workout ID", value = """
                                            {
                                                "statusCode": 400,
                                                "errorMessage": "bad request",
                                                "exceptionClassName": "org.springframework.web.method.annotation.MethodArgumentTypeMismatchException",
                                                "path": "",
                                                "timestamp": "2025-03-14T12:00:00.000Z",
                                                "errors": []
                                            }
                                            """),
                                    @ExampleObject(name = "Invalid Exercise ID", value = """
                                            {
                                                "statusCode": 400,
                                                "errorMessage": "bad request",
                                                "exceptionClassName": "org.springframework.web.method.annotation.MethodArgumentTypeMismatchException",
                                                "path": "",
                                                "timestamp": "2025-03-14T12:00:00.000Z",
                                                "errors": []
                                            }
                                            """)
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - Workout-exercise association does not exist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = {
                                    @ExampleObject(name = "Workout ID Not Found", value = """
                                            {
                                                "statusCode": 404,
                                                "errorMessage": "not found",
                                                "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                                "path": "",
                                                "exceptionMessage": "Workout with ID 10 was not found",
                                                "timestamp": "2025-03-14T12:00:00.000Z"
                                            }
                                            """),
                                    @ExampleObject(name = "Exercise ID Not Found", value = """
                                            {
                                                "statusCode": 404,
                                                "errorMessage": "not found",
                                                "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                                "path": "",
                                                "exceptionMessage": "Exercise with ID 20 was not found",
                                                "timestamp": "2025-03-14T12:00:00.000Z"
                                            }
                                            """),
                                    @ExampleObject(name = "Workout-Exercise association not found", value = """
                                            {
                                                "statusCode": 404,
                                                "errorMessage": "not found",
                                                "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                                "path": "",
                                                "exceptionMessage": "The exercise 2 does not belong to workout 3",
                                                "timestamp": "2025-03-14T12:00:00.000Z"
                                            }
                                            """)
                            }
                    )
            )
    })
    @GetMapping("api/workout/{workoutId}/exercise/{exerciseId}")
    public ResponseEntity<WorkoutExerciseReadResponse> findByWorkoutAndExercise(
            @Parameter(required = true, example = "1") @PathVariable int workoutId,
            @Parameter(required = true, example = "2") @PathVariable int exerciseId) {
        return ResponseEntity.ok(workoutExerciseService.findByWorkoutAndExercise(workoutId, exerciseId));
    }
}