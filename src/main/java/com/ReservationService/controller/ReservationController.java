package com.ReservationService.controller;


import com.ReservationService.model.Reservation;
import com.ReservationService.service.NotificationPublisher;
import com.ReservationService.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.function.ThrowingSupplier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final NotificationPublisher notificationPublisher;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck(@RequestHeader(value = "X-User-ID") String user) {
        System.out.println("User: " + user);
        return ResponseEntity.ok("Reservation Service is up and running");
    }

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
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestHeader(value = "X-User-ID") UUID userId,
            @RequestHeader(value = "X-User-Mail") String user,
            @RequestBody Reservation reservation
    ) {

        return handleRequestProcess(() ->
                reservationService.createReservation(reservation, userId, user)
        );
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
            return ResponseEntity.status(500).body(ReservationResponse.builder().message("Internal Server Error: " + e).build());
        }
    }

}
