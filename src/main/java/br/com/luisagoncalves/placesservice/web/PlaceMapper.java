package br.com.luisagoncalves.placesservice.web;

import br.com.luisagoncalves.placesservice.api.PlaceResponse;
import br.com.luisagoncalves.placesservice.domain.Place;

public class PlaceMapper {
    public static PlaceResponse fromPlaceToResponse(Place place) {
        return new PlaceResponse(place.id(), place.name(), place.slug(), place.state(), place.createdAt(), place.updatedAt());
    }
}
