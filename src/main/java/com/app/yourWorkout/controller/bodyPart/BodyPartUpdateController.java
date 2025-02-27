package com.app.yourWorkout.controller.bodyPart;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.service.BodyPartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bodyParts")
@AllArgsConstructor
public class BodyPartUpdateController {
    private final BodyPartService bodyPartService;

    //UPDATE
    @PutMapping("{id}")
    public ResponseEntity<BodyPartDTO> updateBodyPart(@PathVariable int id, @RequestBody BodyPartDTO bodyPartDTO){
        return ResponseEntity.ok(bodyPartService.updateBodyPart(id, bodyPartDTO));
    }
}
