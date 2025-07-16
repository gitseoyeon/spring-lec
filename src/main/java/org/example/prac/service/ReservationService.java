package org.example.prac.service;

import lombok.RequiredArgsConstructor;
import org.example.prac.dto.ReservationDto;
import org.example.prac.model.Event;
import org.example.prac.model.Reservation;
import org.example.prac.repository.EventRepository;
import org.example.prac.repository.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final EventRepository eventRepository;

    public Reservation create(Long eventId, ReservationDto reservationDto) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NoSuchElementException("이벤트가 존재하지 않습니다."));
        Reservation reservation = new Reservation();

        reservation.setAttendeeName(reservationDto.getAttendeeName());
        reservation.setSeat(reservationDto.getSeats());
        reservation.setEvent(event);

        return reservationRepository.save(reservation);
    }

    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("예약 정보가 없습니다."));
    }

    public Page<Reservation> getAllReservations(Long eventId, Pageable pageable) {
        return reservationRepository.findByEventId(eventId, pageable);
    }

    public Reservation update(Long id, ReservationDto reservationDto) {
        Reservation reservation = getReservation(id);

        reservation.setAttendeeName(reservationDto.getAttendeeName());
        reservation.setSeat(reservationDto.getSeats());

        return reservation;
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
}
