package com.app.yourWorkout.controller.bodyPart;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.handler.responseError.ResponseErrorBusiness;
import com.app.yourWorkout.handler.responseError.ResponseErrorGeneral;
import com.app.yourWorkout.service.BodyPartService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/bodyParts")
@AllArgsConstructor
@Tag(name = "bodyPart", description = "the body part CRUD controllers")
public class BodyPartCreateController {
    private final BodyPartService bodyPartService;

    @Operation(
            summary = "save a new body part",
            description = "received a body part DTO and save a new body part in database"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BodyPartDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "upper arms"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "conflict",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = @ExampleObject(value = """
                                            {
                                                "statusCode": 409,
                                                "errorMessage": "conflict",
                                                "exceptionClassName": "com.app.yourWorkout.exception.DuplicateDataException",
                                                "path": "",
                                                "exceptionMessage": "the body part with name lower legs already exist",
                                                "timestamp": "2025-03-13T16:47:45.714593918-03:00"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "bad request",
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
                                            "field name can not be empty or blank"
                                        ],
                                        "timestamp": "2025-03-13T16:47:45.714593918-03:00"
                                    }
                                    """
                            )

                    )
            )

    })
    @PostMapping
    public ResponseEntity<BodyPartDTO> saveBodyPart(
            @Valid
            @RequestBody
            @Parameter(
                    required = true,
                    example = """
                                {
                                    "name": "lower body"
                                }
                            """,
                    schema = @Schema(implementation = BodyPartDTO.class)
            )
            BodyPartDTO bodyPartDTO
    ) {

        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri())
                .body(bodyPartService.saveBodyPart(bodyPartDTO));
    }


    @Operation(
            summary = "save secondary body parts for one exercise",
            description = "received a list of secondary body part and a exercise id to save the list to exercise"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BodyPartDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "name": "upper arms"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "conflict",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = {
                                    @ExampleObject(value = """
                                            {
                                                "statusCode": 409,
                                                "errorMessage": "conflict",
                                                "exceptionClassName": "com.app.yourWorkout.exception.CollectionEmptyException",
                                                "path": "",
                                                "exceptionMessage": "list of body parts secondary names is empty",
                                                "timestamp": "2025-03-13T16:47:45.714593918-03:00"
                                            }
                                            """
                                    ),
                                    @ExampleObject(value = """
                                            {
                                                "statusCode": 409,
                                                "errorMessage": "conflict",
                                                "exceptionClassName": "com.app.yourWorkout.exception.CollectionEmptyException",
                                                "path": "",
                                                "exceptionMessage": "No valid body parts found for the given names.",
                                                "timestamp": "2025-03-13T16:47:45.714593918-03:00"
                                            }
                                            """
                                    )
                            }


                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseErrorBusiness.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": 404,
                                        "errorMessage": "not found",
                                        "exceptionClassName": "com.app.yourWorkout.exception.DataNotFoundException",
                                        "path": "",
                                        "exceptionMessage": "the exercise: 10  was not found",
                                        "timestamp": "2025-03-13T16:47:45.714593918-03:00"
                                    }
                                    """
                            )

                    )
            )

    })
    @PostMapping("secondaryBodyParts/exercise/{exerciseId}")
    public ResponseEntity<Set<BodyPartDTO>> saveSecondaryBodyPartsByExercise(
            @PathVariable("exerciseId")
            @Parameter(
                    required = true,
                    example = "5"
            )
            int exerciseId,

            @RequestBody
            @Parameter(
                    required = true,
                    example = """
                                [
                                    "back",
                                    "chest",
                                    "lower legs"
                                ]
                            """
            )
            List<String> secondaryBodyPartNames)
    {
        return ResponseEntity
                .created( ServletUriComponentsBuilder.fromCurrentRequest().build().toUri() )
                .body(bodyPartService.saveSecondaryBodyPartsByExercise(exerciseId, secondaryBodyPartNames));
    }
}
