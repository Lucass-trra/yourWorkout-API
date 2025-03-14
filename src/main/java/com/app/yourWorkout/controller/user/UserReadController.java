package com.app.yourWorkout.controller.user;

import com.app.yourWorkout.DTO.response.UserReadResponse;
import com.app.yourWorkout.handler.responseError.ResponseErrorBusiness;
import com.app.yourWorkout.handler.responseError.ResponseErrorGeneral;
import com.app.yourWorkout.service.UserService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
@Tag(name = "User", description = "user CRUD controllers")
public class UserReadController {
    private final UserService userService;

    @Operation(
            summary = "Get a user by ID",
            description = "Retrieves a user from the database using their unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - User found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserReadResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "John Doe",
                                        "email": "john.doe@example.com",
                                        "sex": "MALE",
                                        "datOfbBirth": "1990-01-01",
                                        "levelOfExperience": "BEGINNER",
                                        "workoutGoal": "WEIGHT_LOSS",
                                        "height": 180,
                                        "weight": 80
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
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """ )
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
                                    """ )
                    )
            )
    })
    @GetMapping("id/{userId}")
    public ResponseEntity<UserReadResponse> getById(
            @Parameter(required = true, example = "1")
            @PathVariable int userId
    ) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @Operation(
            summary = "Get a user by username",
            description = "Retrieves a user from the database using their username."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - User found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserReadResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "John Doe",
                                        "email": "john.doe@example.com",
                                        "sex": "MALE",
                                        "datOfbBirth": "1990-01-01",
                                        "levelOfExperience": "BEGINNER",
                                        "workoutGoal": "WEIGHT_LOSS",
                                        "height": 180,
                                        "weight": 80
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
                                        "exceptionMessage": "Failed to convert value of type 'java.lang.Integer' to required type 'java.lang.String' for parameter 'username'",
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """ )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - User with the given username does not exist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 404,
                                        "errorMessage": "not found",
                                        "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                        "path": "",
                                        "exceptionMessage": "User with username 'john_doe' was not found",
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """ )
                    )
            )
    })
    @GetMapping("username/{username}")
    public ResponseEntity<UserReadResponse> getByUsername(
            @Parameter(required = true, example = "john_doe")
            @PathVariable String username
    ) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @Operation(
            summary = "Get a user by email",
            description = "Retrieves a user from the database using their email address."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - User found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserReadResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "John Doe",
                                        "email": "john.doe@example.com",
                                        "sex": "MALE",
                                        "datOfbBirth": "1990-01-01",
                                        "levelOfExperience": "BEGINNER",
                                        "workoutGoal": "WEIGHT_LOSS",
                                        "height": 180,
                                        "weight": 80
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
                                        "exceptionMessage": "Failed to convert value of type 'java.lang.Integer' to required type 'java.lang.String' for parameter 'email'",
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """ )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found - User with the given email does not exist",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 404,
                                        "errorMessage": "not found",
                                        "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                        "path": "",
                                        "exceptionMessage": "User with email 'john.doe@example.com' was not found",
                                        "timestamp": "2025-03-14T12:00:00.000Z"
                                    }
                                    """ )
                    )
            )
    })
    @GetMapping("email/{email}")
    public ResponseEntity<UserReadResponse> getByEmail(
            @Parameter(required = true, example = "john.doe@example.com")
            @PathVariable String email)
    {
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
