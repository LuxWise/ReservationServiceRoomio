package com.ReservationService.service;

import com.ReservationService.dto.NotificationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationPublisher {


    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void publishNotification(String email, String hotelName){
        try {
            NotificationRequest request = new NotificationRequest();
            request.setTo(email);
            request.setSubject("¡Reserva confirmada en " + hotelName + "!");
            request.setContent("<h1>Gracias por reservar con Roomio</h1><p>Tu reserva ha sido procesada correctamente.</p>");

            String message = objectMapper.writeValueAsString(request);

            rabbitTemplate.convertAndSend("notifications_service.queue", message);
        } catch (Exception e) {
            System.err.println("❌ Error al enviar notificación: " + e.getMessage());
        }
    }
}
