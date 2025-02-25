package com.app.yourWorkout.controller.bodyPart;

import com.app.yourWorkout.entities.BodyPart;
import com.app.yourWorkout.service.BodyPartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bodyParts")
public class BodyPartCreateController {
    private final BodyPartService bodyPartService;

    public BodyPartCreateController(BodyPartService bodyPartService) {
        this.bodyPartService = bodyPartService;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<BodyPart> saveBodyPart(@RequestBody BodyPart bodyPart){
        return ResponseEntity.ok(bodyPartService.saveBodyPart(bodyPart));
    }
}
