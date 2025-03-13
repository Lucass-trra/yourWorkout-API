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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/bodyParts")
@AllArgsConstructor
@Tag(name = "bodyPart", description = "the body part CRUD controllers")
public class BodyPartUpdateController {
    private final BodyPartService bodyPartService;

    @Operation(
            summary = "update a body part",
            description = "received a body part DTO and body part id to update it"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ok",
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
                                                "exceptionMessage": "the body part: lats already exists, can not update it",
                                                "timestamp": "2025-03-13T16:47:45.714593918-03:00"
                                            }
                                            """
                            )
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
                                                "exceptionMessage": "the body part: 10 was not found to update",
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
    @PutMapping("id/{id}")
    public ResponseEntity<BodyPartDTO> updateBodyPart(
            @PathVariable("id")
            @Parameter(required = true, example = "20")
            int id,

            @Valid
            @RequestBody
            @Parameter(
                    required = true,
                    schema = @Schema(implementation = BodyPartDTO.class),
                    example = """
                            {
                                "name": "upper arms"
                            }
                            """
            )
            BodyPartDTO bodyPartDTO)
    {
        return ResponseEntity
                .ok()
                .location(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .build()
                        .toUri()
                )
                .body(bodyPartService.updateBodyPart(id, bodyPartDTO));
    }
}
