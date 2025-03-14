package com.app.yourWorkout.controller.workout;

import com.app.yourWorkout.DTO.request.workout.WorkoutCreateRequest;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "Workout", description = "workout CRUD controllers")
public class WorkoutCreateController {
    private final WorkoutService workoutService;

    @Operation(
            summary = "Create a new workout for a user",
            description = "Creates a new workout and associates it with a user."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created - Workout successfully created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = WorkoutReadResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "Full Body Workout",
                                        "isCurrent": true,
                                        "description": "A comprehensive workout routine."
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
                                                "exceptionMessage": "Failed to convert value of type 'java.lang.String' to required type 'int' for parameter 'userId'",
                                                "timestamp": "2025-03-14T12:00:00.000Z",
                                                "errors": []
                                            }
                                            """)
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict - workout with the given name already exists by given user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 409,
                                        "errorMessage": "conflict",
                                        "exceptionClassName": "com.app.yourWorkout.exception.DuplicateDataException",
                                        "path": "",
                                        "exceptionMessage": "The user with username: john_doe already exists in the database",
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - User with the given ID does not exist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 404,
                                        "errorMessage": "not found",
                                        "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                        "path": "",
                                        "exceptionMessage": "User with ID 10 was not found",
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """)
                    )
            )
    })
    @PostMapping("api/workouts/user/{userId}")
    public ResponseEntity<WorkoutReadResponse> saveByUserId(
            @Parameter(required = true, example = "1") @PathVariable int userId,
            @Parameter(required = true, example = """
                    {
                        "name": "Full Body Workout",
                        "isCurrent": true,
                        "description": "A comprehensive workout routine."
                    }
                    """
            )
            @Valid @RequestBody WorkoutCreateRequest workoutRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(workoutService.saveByUserId(userId, workoutRequest));
    }
}