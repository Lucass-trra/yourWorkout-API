package com.app.yourWorkout.controller.bodyPart;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.DTO.response.ExerciseReadResponse;
import com.app.yourWorkout.entities.Exercise;
import com.app.yourWorkout.service.BodyPartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@RestController
@RequestMapping("api/bodyParts")
@AllArgsConstructor
public class BodyPartCreateController {
    private final BodyPartService bodyPartService;

    //CREATE
    @PostMapping
    public ResponseEntity<BodyPartDTO> saveBodyPart(@Valid @RequestBody BodyPartDTO bodyPartDTO){
        return ResponseEntity.ok(bodyPartService.saveBodyPart(bodyPartDTO));
    }

    @PostMapping("secondaryBodyParts/exercise/{id}")
    public ResponseEntity<ExerciseReadResponse> saveSecondaryBodyPartsByExercise(@PathVariable int exerciseId,
                                                                                 @Valid
                                                                     @RequestBody
                                                                     List<String> secondaryBodyPartNames)
    {
        return ResponseEntity.ok(bodyPartService
                .saveSecondaryBodyPartsByExercise(exerciseId, secondaryBodyPartNames));
    }
}
