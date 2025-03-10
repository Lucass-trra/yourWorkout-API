package com.app.yourWorkout.repository;

import com.app.yourWorkout.entities.BodyPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BodyPartRepository extends JpaRepository<BodyPart, Integer> {
    //READ
    Optional<BodyPart> findByName(String name);

    List<BodyPart> findAllByNameIn(List<String> names);


    //DELETE
    void deleteByName(String name);

    boolean existsById(int id);

    boolean existsByName(String name);
}
