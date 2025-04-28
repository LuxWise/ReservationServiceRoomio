package com.ReservationService.controller;


import com.ReservationService.model.Reservation;
import com.ReservationService.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.function.ThrowingSupplier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(
            @PathVariable UUID reservationId) {
        return ResponseEntity.ok(reservationService.getReservationById(reservationId));
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation() {
        return handleRequestProcess(() -> {
            return ReservationResponse.builder()
                    .message("Reservation created successfully")
                    .build();
        });
    }

    @PatchMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> updateReservation() {
        return handleRequestProcess(() -> {
            return ReservationResponse.builder()
                    .message("Reservation modify successfully")
                    .build();
        });
    }

    @PatchMapping("cancel/{reservationId}")
    public ResponseEntity<ReservationResponse> cancelReservation() {
        return handleRequestProcess(() -> {
            return ReservationResponse.builder()
                    .message("Reservation cancel successfully")
                    .build();
        });
    }

    private ResponseEntity<ReservationResponse> handleRequestProcess(ThrowingSupplier<ReservationResponse> supplier) {
        try {
            return ResponseEntity.ok(supplier.get());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(ReservationResponse.builder().message("Internal Server Error").build());
        }
    }

}
