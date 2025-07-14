package org.example.prac.repository;

import org.example.prac.model.User_todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTodoRepository extends JpaRepository<User_todo, Integer> {
    Optional<User_todo> findByUsername(String username);
}
