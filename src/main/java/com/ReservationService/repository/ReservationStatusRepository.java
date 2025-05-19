package com.ReservationService.repository;

import com.ReservationService.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationStatusRepository extends JpaRepository<ReservationStatus, Long> {}
