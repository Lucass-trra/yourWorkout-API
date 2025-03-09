package com.app.yourWorkout.controller.bodyPart;

import com.app.yourWorkout.DTO.BodyPartDTO;
import com.app.yourWorkout.service.BodyPartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bodyParts")
@AllArgsConstructor
public class BodyPartReadController {
    private final BodyPartService bodyPartService;

    //READ
    @GetMapping("id/{id}")
    public ResponseEntity<BodyPartDTO> getById(@PathVariable int id){
        return ResponseEntity.ok(bodyPartService.findById(id));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<BodyPartDTO> getByName(@PathVariable String name){
        return ResponseEntity.ok(bodyPartService.findByName(name));
    }
}
