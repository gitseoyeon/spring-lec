package org.example.prac.service;

import lombok.RequiredArgsConstructor;
import org.example.prac.dto.EventDto;
import org.example.prac.model.Event;
import org.example.prac.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final EventRepository eventRepository;

    public Page<Event> search(String name, String location, LocalDateTime from,
                              LocalDateTime to, Pageable pageable) {
        Specification<Event> specification = Specification.allOf();

        if(name != null) specification = specification.and((root, query, criteriaBuilder) 
                -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        if(location != null) specification = specification.and((root, query, criteriaBuilder) 
                -> criteriaBuilder.like(root.get("location"), "%" + location + "%"));
        if(from != null) specification = specification.and((root, query, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("eventDate"), from));
        if(to != null) specification = specification.and((root, query, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("eventDate"), to));

        return eventRepository.findAll(specification, pageable);
    }

    public Event getById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new NoSuchElementException("이벤트가 없습니다."));
    }

    public Event create(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setEventDate(eventDto.getEventDate());
        event.setLocation(eventDto.getLocation());

        return eventRepository.save(event);
    }

    public Event update(Long id, EventDto eventDto) {
        Event event = getById(id);

        event.setName(eventDto.getName());
        event.setLocation(eventDto.getLocation());
        event.setEventDate(eventDto.getEventDate());

        return event;
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
