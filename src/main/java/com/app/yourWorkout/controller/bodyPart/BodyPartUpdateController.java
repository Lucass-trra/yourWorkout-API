package com.app.yourWorkout.controller.bodyPart;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.service.BodyPartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/bodyParts")
@AllArgsConstructor
public class BodyPartUpdateController {
    private final BodyPartService bodyPartService;

    //UPDATE
    @PutMapping("{id}")
    public ResponseEntity<BodyPartDTO> updateBodyPart(@PathVariable int id,
                                                      @Valid @RequestBody BodyPartDTO bodyPartDTO){
        return ResponseEntity.ok(bodyPartService.updateBodyPart(id, bodyPartDTO));
    }
}
