package br.com.luisagoncalves.placesservice.api;

import jakarta.validation.constraints.NotBlank;

public record PlaceRequest(
        @NotBlank
        String name,
        @NotBlank
        String state) {

}
