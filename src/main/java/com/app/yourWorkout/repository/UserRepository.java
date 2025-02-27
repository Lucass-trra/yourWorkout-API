package com.app.yourWorkout.repository;

import com.app.yourWorkout.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //READ
    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    //DELETE
    void deleteByName(String name);

    void deleteByEmail(String email);

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
