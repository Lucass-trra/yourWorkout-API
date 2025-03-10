package com.app.yourWorkout.controller.bodyPart;

import com.app.yourWorkout.service.BodyPartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bodyParts")
@AllArgsConstructor
public class BodyPartDeleteController {
    private final BodyPartService bodyPartService;

    //DELETE
    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        bodyPartService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("name/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable String name){
        bodyPartService.deleteByName(name);
        return ResponseEntity.ok().build();
    }

}
