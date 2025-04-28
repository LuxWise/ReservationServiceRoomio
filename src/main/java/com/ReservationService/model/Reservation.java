package com.ReservationService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column( name = "reservation_start", nullable = false)
    private LocalDateTime reservationStart;

    @Column(name = "reservation_end", nullable = false)
    private LocalDateTime reservationEnd;

    @Column(name = "user_id", nullable = false)
    private UUID roomId;

    @ManyToOne
    @JoinColumn(name = "reservation_status", nullable = false)
    private ReservationStatus reservation_status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
