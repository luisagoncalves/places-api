package br.com.luisagoncalves.placesservice.domain;

import com.github.slugify.Slugify;

import br.com.luisagoncalves.placesservice.api.PlaceRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PlaceService {
    private PlaceRepository placeRepository;
    private final Slugify slg;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.slg = Slugify.builder().build();
    }

    public Mono<Place> create(PlaceRequest placeRequest) {
        var place = new Place(null, placeRequest.name(), slg.slugify(placeRequest.name()), placeRequest.state(),
                null, null);
        return placeRepository.save(place);
    }

    public Flux<Place> listAll() {
        return placeRepository.findAll();
    }

    public Mono<Place> updatePlace(Long id, PlaceRequest placeRequest) {
        var place = new Place(null, placeRequest.name(), slg.slugify(placeRequest.name()), placeRequest.state(),
                null, null);
        return placeRepository.findById(id)
                .flatMap(existingPlace -> placeRepository.save(place)
                        .then(Mono.just(existingPlace)));
    }

    public Mono<Place> deleteById(Long id) {
        return placeRepository.findById(id)
                .flatMap(existingPlace -> placeRepository.delete(existingPlace)
                        .then(Mono.just(existingPlace)));
    }
}
