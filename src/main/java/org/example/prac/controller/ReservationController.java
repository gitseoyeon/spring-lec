package org.example.prac.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prac.dto.ReservationDto;
import org.example.prac.model.Reservation;
import org.example.prac.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/api/events/{eventId}/reservations")
    public Reservation create(@PathVariable Long eventId, @RequestBody @Valid ReservationDto reservationDto) {
        return reservationService.create(eventId, reservationDto);
    }

    @GetMapping("/api/events/{eventId}/reservations")
    public Page<Reservation> listByEvent(@PathVariable Long eventId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return reservationService.getAllReservations(eventId, pageable);
    }
    @GetMapping("/api/reservation/{id}")
    public Reservation get(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    @PutMapping("/api/reservation/{id}")
    public Reservation update(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        return reservationService.update(id, reservationDto);
    }

    @DeleteMapping("/api/reservation/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        reservationService.delete(id);

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }
}
