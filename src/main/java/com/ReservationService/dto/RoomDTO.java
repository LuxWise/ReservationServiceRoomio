package com.ReservationService.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RoomDTO {
    private UUID id;
    private String name;
    private String description;
    private int numberOfBeds;
    private int numberOfBathrooms;
    private int numberOfPeople;
    private int numberOfPets;
    private int numberOfKids;
    private boolean availableKids;
    private boolean availablePets;
    private double price;
    private RoomTypeDTO roomTypetId;
    private HotelDTO hotelId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;
}
