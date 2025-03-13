package com.app.yourWorkout.controller.bodyPart;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bodyParts")
@AllArgsConstructor
@Tag(name = "bodyPart", description = "the body part CRUD controllers|")
public class BodyPartDeleteController {
    private final BodyPartService bodyPartService;

    @Operation(
            summary = "delete a body part by ID",
            description = "received a body part id and delete it in database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "no content"),
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
                                        "exceptionMessage": "the body part: 10 was not found to delete",
                                        "timestamp": "2025-03-13T16:47:45.714593918-03:00"
                                    }
                                    """
                            )

                    )
            )

    })
    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(
                    example = "12",
                    required = true
            )
            @PathVariable("id")
            int id
    ){
        bodyPartService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "delete a body part by name",
            description = "received a body part name and delete it in database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "no content"),
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
                                        "exceptionMessage": "the body part: pump was not found to delete",
                                        "timestamp": "2025-03-13T16:47:45.714593918-03:00"
                                    }
                                    """
                            )

                    )
            )

    })
    @DeleteMapping("name/{name}")
    public ResponseEntity<Void> deleteByName(
            @Parameter(
                    examples = {
                            @ExampleObject(name = "back"),
                            @ExampleObject(name = "chest"),
                            @ExampleObject(name = "lower arms"),
                            @ExampleObject(name = "upper arms")
                    },
                    required = true
            )
            @PathVariable("name")
            String name){
        bodyPartService.deleteByName(name);
        return ResponseEntity.ok().build();
    }

}
