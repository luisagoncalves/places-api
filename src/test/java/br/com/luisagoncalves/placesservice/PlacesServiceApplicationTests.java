package br.com.luisagoncalves.placesservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.luisagoncalves.placesservice.api.PlaceRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PlacesServiceApplicationTests {
	@Autowired
	WebTestClient webTestClient;

	@Test
	public void testCreatePlace() {
		var name = "Valid Name";
		var state = "Valid State";

		webTestClient
				.post()
				.uri("/places")
				.bodyValue(
						new PlaceRequest(name, state))
				.exchange()
				.expectBody()
				.jsonPath("name").isEqualTo(name)
				.jsonPath("state").isEqualTo(state)
				.jsonPath("createdAt").isNotEmpty()
				.jsonPath("updatedAt").isNotEmpty();
	}

	@Test
	public void testCreatePlaceFailure() {
		var name = "";
		var state = "";

		webTestClient
				.post()
				.uri("/places")
				.bodyValue(
						new PlaceRequest(name, state))
				.exchange()
				.expectStatus().isBadRequest();
	}
}
