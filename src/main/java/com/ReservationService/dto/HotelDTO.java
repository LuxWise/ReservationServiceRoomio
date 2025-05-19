package com.ReservationService.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class HotelDTO {
    private UUID id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phoneNumber;
    private String email;
    private String coordinatesLatitude;
    private String coordinatesLongitude;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
