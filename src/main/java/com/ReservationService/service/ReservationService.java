package com.ReservationService.service;

import com.ReservationService.controller.ReservationResponse;
import com.ReservationService.dto.RoomDTO;
import com.ReservationService.model.Reservation;
import com.ReservationService.model.ReservationStatus;
import com.ReservationService.repository.ReservationRepository;
import com.ReservationService.repository.ReservationStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationStatusRepository reservationStatusRepository;
    private final NotificationPublisher notificationPublisher;
    private final HotelClient hotelClient;

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(UUID id){
        return reservationRepository.findById(id).orElse(null);
    }

    public ReservationResponse createReservation(Reservation reservation, UUID userId, String user){
        RoomDTO rooms = hotelClient.getRoomById(reservation.getRoomId());
        ReservationStatus reservationStatus = reservationStatusRepository.findById(1L).orElse(null);

        System.out.println(rooms);

        Reservation reservation_data = new Reservation();
        reservation_data.setReservationStart(reservation.getReservationStart());
        reservation_data.setReservationEnd(reservation.getReservationEnd());
        reservation_data.setUserId(userId);
        reservation_data.setHotelId(rooms.getHotelId().getId());
        reservation_data.setHotelName(rooms.getHotelId().getName());
        reservation_data.setRoomId(rooms.getId());
        reservation_data.setRoomType(rooms.getRoomTypetId().getId());
        reservation_data.setReservationStatus(reservationStatus);
        reservation_data.setCreatedAt(LocalDateTime.now());

        Reservation saved = reservationRepository.save(reservation_data);

        if (saved.getId() != null) {
            notificationPublisher.publishNotification(user, "New reservation created");
        }

        return ReservationResponse.builder().message("Reservation created successfully").build();
    }
}
