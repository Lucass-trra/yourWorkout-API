package com.app.yourWorkout.controller.bodyPart;

import com.app.yourWorkout.entities.BodyPart;
import com.app.yourWorkout.service.BodyPartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bodyParts")
public class BodyPartUpdateController {
    private final BodyPartService bodyPartService;

    public BodyPartUpdateController(BodyPartService bodyPartService) {
        this.bodyPartService = bodyPartService;
    }

    //UPDATE
    @PutMapping("{id}")
    public ResponseEntity<BodyPart> updateBodyPart(@PathVariable int id, @RequestBody BodyPart bodyPart){
        return ResponseEntity.ok(bodyPartService.updateBodyPart(id, bodyPart));
    }
}
