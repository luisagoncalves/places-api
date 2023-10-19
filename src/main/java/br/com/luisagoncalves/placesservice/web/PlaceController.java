package br.com.luisagoncalves.placesservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.luisagoncalves.placesservice.api.PlaceRequest;
import br.com.luisagoncalves.placesservice.api.PlaceResponse;
import br.com.luisagoncalves.placesservice.domain.PlaceService;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PlaceResponse> create(@Valid @RequestBody PlaceRequest request) {
        return placeService.create(request).map(PlaceMapper::fromPlaceToResponse);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<PlaceResponse> listAll() {
        return placeService.listAll().map(PlaceMapper::fromPlaceToResponse);
    }

    @PutMapping(value = "/{placeId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<PlaceResponse> update(@PathVariable(value = "placeId") Long placeId,
            @RequestBody PlaceRequest request) {
        return placeService.updatePlace(placeId, request).map(PlaceMapper::fromPlaceToResponse);
    }

    @DeleteMapping("/{placeId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable(value = "placeId") Long placeId) {
        return placeService.deleteById(placeId)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
