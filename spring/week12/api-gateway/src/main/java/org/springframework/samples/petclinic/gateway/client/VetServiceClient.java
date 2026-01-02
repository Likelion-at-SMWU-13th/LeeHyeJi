package org.springframework.samples.petclinic.gateway.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VetServiceClient {

    private final RestTemplate restTemplate;

    @Value("${services.vet.url}")
    private String vetServiceUrl;

    @Value("${services.monolith.url}")
    private String monolithUrl;

    public VetServiceClient(@Qualifier("vetRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "vet-service", fallbackMethod = "getVetPageFallback")
    public String getVetPage(String path) {
        return restTemplate.getForObject(vetServiceUrl + path, String.class);
    }

    public String getVetPageFallback(String path, Exception e) {
        return restTemplate.getForObject(monolithUrl + path, String.class);
    }
}