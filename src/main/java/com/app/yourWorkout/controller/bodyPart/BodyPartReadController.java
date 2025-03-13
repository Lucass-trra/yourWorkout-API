package com.app.yourWorkout.controller.bodyPart;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.handler.responseError.ResponseErrorBusiness;
import com.app.yourWorkout.service.BodyPartService;
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

@RestController
@RequestMapping("api/bodyParts")
@AllArgsConstructor
@Tag(name = "bodyPart", description = "the body part CRUD controllers")
public class BodyPartReadController {
    private final BodyPartService bodyPartService;

    @Operation(
            summary = "read a body part by id",
            description = "received a body part id and get it in database"
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
                                        "exceptionMessage": "the body part: 10 was not found",
                                        "timestamp": "2025-03-13T16:47:45.714593918-03:00"
                                    }
                                    """
                            )

                    )
            )

    })
    @GetMapping("id/{id}")
    public ResponseEntity<BodyPartDTO> getById(
            @Parameter(required = true, example = "20")
            @PathVariable("id")
            int id){
        return ResponseEntity.ok(bodyPartService.findById(id));
    }

    @Operation(
            summary = "read a body part by name",
            description = "received a body part name and get it in database"
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
                                        "exceptionMessage": "the body part: 10 was not found",
                                        "timestamp": "2025-03-13T16:47:45.714593918-03:00"
                                    }
                                    """
                            )

                    )
            )

    })
    @GetMapping("name/{name}")
    public ResponseEntity<BodyPartDTO> getByName(
            @Parameter(required = true, example = "20")
            @PathVariable("name")
            String name){
        return ResponseEntity.ok(bodyPartService.findByName(name));
    }
}
