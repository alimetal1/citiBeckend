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
        String apiUrl = API_BASE_URL + word + "/synonyms";

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los sinónimos");
        }
    }
}


