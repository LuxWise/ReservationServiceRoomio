package com.ReservationService.dto;

import lombok.Data;

@Data
public class NotificationRequest {
    private String to;
    private String subject;
    private String content;
}
