package com.ReservationService.service;

import com.ReservationService.dto.RoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelClient {

    private final RestTemplate restTemplate;

    public List<RoomDTO> getAllRooms(){
        String url = "http://hotel-service:8082/hotel/rooms";
        ResponseEntity<RoomDTO[]> response = restTemplate.getForEntity(url, RoomDTO[].class);
        assert response.getBody() != null;
        return List.of(response.getBody());
    }

    public RoomDTO getRoomById(UUID roomId) {
        String url = "http://hotel-service:8082/hotel/rooms/" + roomId;
        ResponseEntity<RoomDTO> response = restTemplate.getForEntity(url, RoomDTO.class);
        return response.getBody();
    }
}
