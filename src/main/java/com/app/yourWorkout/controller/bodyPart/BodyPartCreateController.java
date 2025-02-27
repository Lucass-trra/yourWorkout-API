package com.app.yourWorkout.controller.bodyPart;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.service.BodyPartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bodyParts")
@AllArgsConstructor
public class BodyPartCreateController {
    private final BodyPartService bodyPartService;

    //CREATE
    @PostMapping
    public ResponseEntity<BodyPartDTO> saveBodyPart(@RequestBody BodyPartDTO bodyPartDTO){
        return ResponseEntity.ok(bodyPartService.saveBodyPart(bodyPartDTO));
    }
}
