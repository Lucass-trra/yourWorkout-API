package com.app.yourWorkout.repository;

import com.app.yourWorkout.entities.BodyPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyPartRepository extends JpaRepository<BodyPart, Integer> {
    //READ
    BodyPart findByName(String name);

    //DELETE
    void deleteByName(String name);
}
