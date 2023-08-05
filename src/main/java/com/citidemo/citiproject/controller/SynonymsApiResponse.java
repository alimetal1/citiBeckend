package com.citidemo.citiproject.controller;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SynonymsApiResponse {

    @JsonProperty("synonyms")
    private List<String> synonyms;

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }
}

