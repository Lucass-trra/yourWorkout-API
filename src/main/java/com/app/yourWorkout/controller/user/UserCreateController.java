package com.app.yourWorkout.controller.user;

import com.app.yourWorkout.DTO.request.user.UserCreateRequest;
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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "User", description = "user CRUD controllers")
public class UserCreateController {
    private final UserService userService;

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created - User successfully created",
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
                    description = "Bad Request - Invalid request body",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorGeneral.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 400,
                                        "errorMessage": "bad request",
                                        "exceptionClassName": "org.springframework.web.bind.MethodArgumentNotValidException",
                                        "path": "",
                                        "exceptionMessage": "Validation failed for argument...",
                                        "timestamp": "2025-03-14T12:00:00.000Z",
                                        "errors": ["'name' is required and can not be empty or blank"]
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflict - User with the given name already exists",
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
            )
    })
    @PostMapping("api/user")
    public ResponseEntity<UserReadResponse> saveUser(
            @Parameter(required = true, example = """
                    {
                        "name": "John Doe",
                        "email": "john.doe@example.com",
                        "password": "securePassword"
                    }
                    """) @Valid @RequestBody UserCreateRequest userRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.saveUser(userRequest));
    }
}