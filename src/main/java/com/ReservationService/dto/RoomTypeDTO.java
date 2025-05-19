package com.ReservationService.dto;

import lombok.Data;

@Data
public class RoomTypeDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
}
