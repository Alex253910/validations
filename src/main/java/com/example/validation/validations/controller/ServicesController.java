package com.example.validation.validations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import com.example.validation.validations.services.ExternalService;

@RestController
@RequestMapping("/aggregate")
@RequiredArgsConstructor
public class ServicesController {

    private ExternalService externalService;

    @Autowired
    public void AggregateController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping
    public Mono<AggregateResult> aggregateResults() {
        // Combinación de los resultados de los servicios externos
        return Mono.zip(
                externalService.servicioExterno1(),
                externalService.servicioExterno2(),
                externalService.servicioExterno3()
        ).map(tuple -> new AggregateResult(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }

    // Clase para el resultado agregado
    private static class AggregateResult {
        private final String servicio1;
        private final String servicio2;
        private final String servicio3;

        public AggregateResult(String servicio1, String servicio2, String servicio3) {
            this.servicio1 = servicio1;
            this.servicio2 = servicio2;
            this.servicio3 = servicio3;
        }

        // Getters para JSON serialization
        public String getServicio1() {
            return servicio1;
        }

        public String getServicio2() {
            return servicio2;
        }

        public String getServicio3() {
            return servicio3;
        }
    }
}
