package com.app.yourWorkout.controller.workout;

import com.app.yourWorkout.DTO.request.workout.WorkoutUpdateRequest;
import com.app.yourWorkout.DTO.response.WorkoutReadResponse;
import com.app.yourWorkout.handler.responseError.ResponseErrorBusiness;
import com.app.yourWorkout.handler.responseError.ResponseErrorGeneral;
import com.app.yourWorkout.service.WorkoutService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Workout", description = "workout CRUD controllers")
public class WorkoutUpdateController {
    private final WorkoutService workoutService;

    @Operation(
            summary = "Update a workout by user ID and workout ID",
            description = "Updates a workout in the database using the user ID and workout ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - Workout successfully updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutReadResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "Updated Full Body Workout",
                                        "isCurrent": false,
                                        "description": "Updated workout description."
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - Invalid request body or path variables",
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
                                                "errors": ["name cannot be empty or blank"]
                                            }
                                            """),
                                    @ExampleObject(name = "Invalid User ID", value = """
                                            {
                                                "statusCode": 400,
                                                "errorMessage": "bad request",
                                                "exceptionClassName": "org.springframework.web.method.annotation.MethodArgumentTypeMismatchException",
                                                "path": "",
                                                "exceptionMessage": "Failed to convert value of type 'java.lang.String' to required type 'int' for parameter 'userId'",
                                                "timestamp": "2025-03-14T12:00:00.000Z",
                                                "errors": []
                                            }
                                            """),
                                    @ExampleObject(name = "Invalid Workout ID", value = """
                                            {
                                                "statusCode": 400,
                                                "errorMessage": "bad request",
                                                "exceptionClassName": "org.springframework.web.method.annotation.MethodArgumentTypeMismatchException",
                                                "path": "",
                                                "exceptionMessage": "Failed to convert value of type 'java.lang.String' to required type 'int' for parameter 'workoutId'",
                                                "timestamp": "2025-03-14T12:00:00.000Z",
                                                "errors": []
                                            }
                                            """)
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - Workout with the given user ID and workout ID does not exist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = {
                                    @ExampleObject(name = "User ID Not Found", value = """
                                            {
                                                "statusCode": 404,
                                                "errorMessage": "not found",
                                                "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                                "path": "",
                                                "exceptionMessage": "User with ID 10 was not found",
                                                "timestamp": "2025-03-14T12:00:00.000Z"
                                            }
                                            """),
                                    @ExampleObject(name = "Workout ID Not Found", value = """
                                            {
                                                "statusCode": 404,
                                                "errorMessage": "not found",
                                                "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                                "path": "",
                                                "exceptionMessage": "Workout with ID 20 was not found",
                                                "timestamp": "2025-03-14T12:00:00.000Z"
                                            }
                                            """)
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict - Workout name already exists for the user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 409,
                                        "errorMessage": "conflict",
                                        "exceptionClassName": "com.app.yourWorkout.exception.DuplicateDataException",
                                        "path": "",
                                        "exceptionMessage": "Workout with name 'Existing Workout' already exists for user ID 1",
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """)
                    )
            )
    })
    @PutMapping("api/workouts/user/{userId}/workout/{workoutId}")
    public ResponseEntity<WorkoutReadResponse> updateByUserId(
            @Parameter(required = true, example = "1") @PathVariable int userId,
            @Parameter(required = true, example = "2") @PathVariable int workoutId,
            @Parameter(required = true, example = """
                    {
                        "name": "Updated Full Body Workout",
                        "isCurrent": false,
                        "description": "Updated workout description."
                    }
                    """
            )
            @Valid @RequestBody WorkoutUpdateRequest workoutRequest) {
        return ResponseEntity.ok(workoutService.updateByUserId(userId, workoutId, workoutRequest));
    }
}