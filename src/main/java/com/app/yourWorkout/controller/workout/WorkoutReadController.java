package com.app.yourWorkout.controller.workout;

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
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/workouts")
@AllArgsConstructor
@Tag(name = "Workout", description = "workout CRUD controllers")
public class WorkoutReadController {
    private final WorkoutService workoutService;

    @Operation(
            summary = "Find a workout by user ID and workout name",
            description = "Retrieves a workout from the database using the user ID and workout name."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - Workout found",
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
                    description = "Bad Request - Invalid path variables",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorGeneral.class),
                            examples = {
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
                                    @ExampleObject(name = "Invalid Workout Name", value = """
                                            {
                                                "statusCode": 400,
                                                "errorMessage": "bad request",
                                                "exceptionClassName": "org.springframework.web.method.annotation.MethodArgumentTypeMismatchException",
                                                "path": "",
                                                "exceptionMessage": "Failed to convert value of type 'java.lang.Integer' to required type 'java.lang.String' for parameter 'name'",
                                                "timestamp": "2025-03-14T12:00:00.000Z",
                                                "errors": []
                                            }
                                            """)
                            }
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - Workout with the given user ID and workout name does not exist",
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
                                    @ExampleObject(name = "Workout Name Not Found", value = """
                                            {
                                                "statusCode": 404,
                                                "errorMessage": "not found",
                                                "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                                "path": "",
                                                "exceptionMessage": "Workout with name 'Full Body Workout' was not found",
                                                "timestamp": "2025-03-14T12:00:00.000Z"
                                            }
                                            """)
                            }
                    )
            )
    })
    @GetMapping("user/{userId}/workout/{name}")
    public ResponseEntity<WorkoutReadResponse> findByUserIdAndName(
            @Parameter(required = true, example = "1") @PathVariable int userId,
            @Parameter(required = true, example = "Full Body Workout") @PathVariable String name) {
        return ResponseEntity.ok(workoutService.findByUserIdAndName(userId, name));
    }

    @Operation(
            summary = "Find all workouts by user ID",
            description = "Retrieves a paginated list of workouts associated with a user ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - Workouts found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Page.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "content": [
                                            {
                                                "name": "Full Body Workout",
                                                "isCurrent": true,
                                                "description": "A comprehensive workout routine."
                                            },
                                            {
                                                "name": "Leg Day",
                                                "isCurrent": false,
                                                "description": "Workout targeting leg muscles."
                                            }
                                        ],
                                        "pageable": {
                                            "sort": {
                                                "empty": true,
                                                "sorted": false,
                                                "unsorted": true
                                            },
                                            "offset": 0,
                                            "pageNumber": 0,
                                            "pageSize": 20,
                                            "paged": true,
                                            "unpaged": false
                                        },
                                        "totalPages": 1,
                                        "totalElements": 2,
                                        "last": true,
                                        "size": 20,
                                        "number": 0,
                                        "sort": {
                                            "empty": true,
                                            "sorted": false,
                                            "unsorted": true
                                        },
                                        "numberOfElements": 2,
                                        "first": true,
                                        "empty": false
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
                                        "exceptionMessage": "Failed to convert value of type 'java.lang.String' to required type 'int' for parameter 'userId'",
                                        "timestamp": "2025-03-14T12:00:00.000Z",
                                        "errors": []
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
    @GetMapping("user/{userId}")
    public ResponseEntity<Page<WorkoutReadResponse>> findAllByUserId(
            @Parameter(required = true, example = "1") @PathVariable int userId,
            @Parameter(example = "/api/workouts/user/1?page=0&size=10&sort=name,asc")
            Pageable pageable
    ) {
        return ResponseEntity.ok(workoutService.findAllByUserId(userId, pageable));
    }

}