package com.example.validation.validations.services;
import reactor.core.publisher.Mono;

public class ExternalService {

    public Mono<String> servicioExterno1() {
        // Simulación de un servicio externo que devuelve un Mono<String>
        return Mono.just("Resultado del servicio externo 1");
    }

    public Mono<String> servicioExterno2() {
        // Simulación de otro servicio externo que devuelve un Mono<Integer>
        return Mono.just("42");
    }

    public Mono<String> servicioExterno3() {
        // Simulación de otro servicio externo que devuelve un Mono<Double>
        return Mono.just("3.14");
    }

}
