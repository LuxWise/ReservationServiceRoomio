package com.ReservationService.service;

import com.ReservationService.model.Reservation;
import com.ReservationService.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private  final ReservationRepository reservationRepository;
    private final NotificationPublisher notificationPublisher;

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(UUID id){
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }
}
