package com.citidemo.citiproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class SynonymsController {
    private final RestTemplate restTemplate;
    private static final String API_BASE_URL = "https://wordsapiv1.p.mashape.com/words/";

    @Autowired
    public SynonymsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/synonyms/{word}")
    public ResponseEntity<String> getSynonyms(@PathVariable String word) {
        String when = "2023-08-07T14:05:22.164Z";
        String encrypted = "8cfdb189e7229b9bea9607beeb58bebcaeb22f0931f993b8";

        String apiUrl = API_BASE_URL + "words/" + word + "/synonyms";

        try {
            String completeUrl = apiUrl + "?when=" + when + "&encrypted=" + encrypted;
            ResponseEntity<String> response = restTemplate.getForEntity(completeUrl, String.class);

            return ResponseEntity.status(response.getStatusCode())
                    .headers(response.getHeaders())
                    .body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los sinónimos");
        }
    }
}


