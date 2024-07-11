package com.example.validation.validations;

import com.example.validation.validations.services.ExternalService;
import com.example.validation.validations.controller.ServicesController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
class ValidationsApplicationTests {

	@Mock
	private ExternalService externalService;

	@InjectMocks
	private ServicesController servicesController;

	@Test
	public void testAggregateResults() {
		// Mocking the external service responses
		when(externalService.servicioExterno1()).thenReturn(Mono.just("Mocked result from service 1"));
		when(externalService.servicioExterno2()).thenReturn(Mono.just("100"));
		when(externalService.servicioExterno3()).thenReturn(Mono.just("5.0"));

		// Test the aggregate endpoint
		servicesController.aggregateResults()
				.as(StepVerifier::create)
				.expectNextMatches(result ->
						result.getServicio1().equals("Mocked result from service 1") &&
								result.getServicio2().equals("100") &&
								result.getServicio3().equals("5.0")
				)
				.verifyComplete();
	}

}
