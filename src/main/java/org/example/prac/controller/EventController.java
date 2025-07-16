package org.example.prac.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prac.dto.EventDto;
import org.example.prac.model.Event;
import org.example.prac.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public Page<Event> list(@RequestParam(required = false) String name,
                            @RequestParam(required = false) String location,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("eventDate").ascending());
        return eventService.search(name, location, from, to, pageable);
    }

    @GetMapping("/{id}")
    public Event get(@PathVariable Long id) {
        return eventService.getById(id);
    }

    @PostMapping
    public Event create(@Valid @RequestBody EventDto eventDto) {
        return eventService.create(eventDto);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @Valid @RequestBody EventDto eventDto) {
        return eventService.update(id, eventDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.ok("삭제 완료");
    }

}
