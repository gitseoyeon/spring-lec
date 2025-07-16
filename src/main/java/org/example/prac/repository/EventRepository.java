package org.example.prac.repository;


import org.example.prac.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventRepository extends JpaRepository<Event, Long> , JpaSpecificationExecutor<Event> {
}
