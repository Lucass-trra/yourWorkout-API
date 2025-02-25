package com.app.yourWorkout.controller.bodyPart;

import com.app.yourWorkout.entities.BodyPart;
import com.app.yourWorkout.service.BodyPartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bodyParts")
public class BodyPartReadController {
    private final BodyPartService bodyPartService;

    public BodyPartReadController(BodyPartService bodyPartService) {
        this.bodyPartService = bodyPartService;
    }

    //READ
    @GetMapping("id/{id}")
    public ResponseEntity<BodyPart> getById(@PathVariable int id){
        return ResponseEntity.ok(bodyPartService.findById(id));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<BodyPart> getByName(@PathVariable String name){
        return ResponseEntity.ok(bodyPartService.findByName(name));
    }
}
