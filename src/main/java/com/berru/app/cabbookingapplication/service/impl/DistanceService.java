package com.berru.app.cabbookingapplication.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Service
public class DistanceService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public double getDistanceInKm(String from, String to) {
        try {
            URI uri = UriComponentsBuilder
                    .fromHttpUrl("https://maps.googleapis.com/maps/api/distancematrix/json")
                    .queryParam("origins", from)
                    .queryParam("destinations", to)
                    .queryParam("key", apiKey)
                    .queryParam("units", "metric")
                    .build()
                    .toUri();

            JsonNode response = restTemplate.getForObject(uri, JsonNode.class);
            JsonNode distanceNode = response
                    .get("rows").get(0)
                    .get("elements").get(0)
                    .get("distance");

            if (distanceNode != null) {
                return distanceNode.get("value").asDouble() / 1000.0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
