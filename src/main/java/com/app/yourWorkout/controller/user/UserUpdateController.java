package com.app.yourWorkout.controller.user;

import com.app.yourWorkout.DTO.request.user.UserUpdateRequest;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Tag(name = "User", description = "user CRUD controllers")
public class UserUpdateController {
    private final UserService userService;

    @Operation(
            summary = "Update a user by ID",
            description = "Updates a user in the database using their unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - User successfully updated",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserReadResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "Updated John Doe",
                                        "email": "updated.john.doe@example.com",
                                        "sex": "FEMALE",
                                        "datOfbBirth": "1995-05-15",
                                        "levelOfExperience": "INTERMEDIATE",
                                        "workoutGoal": "MUSCLE_GAIN",
                                        "height": 170,
                                        "weight": 70
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
                                                "errors": ["'name' cannot be empty or blank"]
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
    @PutMapping("api/user/{userId}")
    public ResponseEntity<UserReadResponse> updateUser(
            @Parameter(required = true, example = "1") @PathVariable int userId,
            @Parameter(required = true, example = """
                    {
                        "name": "Updated John Doe",
                        "email": "updated.john.doe@example.com",
                        "password": "securePassword",
                        "sex": "FEMALE",
                        "dateOfBirth": "1995-05-15",
                        "levelOfExperience": "INTERMEDIATE",
                        "workoutGoal": "MUSCLE_GAIN",
                        "height": 170,
                        "weight": 70
                    }
                    """) @Valid @RequestBody UserUpdateRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }
}