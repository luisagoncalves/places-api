package br.com.luisagoncalves.placesservice.api;

import java.time.LocalDateTime;

public record PlaceResponse(
        Long id,
        String name,
        String slug,
        String state,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
