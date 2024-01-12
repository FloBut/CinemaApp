package com.example.CinemaApp.B_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
//in clasa asta am nevoie sa imi url prin care voi cere in functie de numele
//filmului alte carcteristici
@Service
public class MovieParamService {

    //cu ajutorul lui rest template ma folosesc de un rest builder
    //pentru a facilita cererile prin http si a converti ce primesc in request
    private RestTemplate restTemplate;
    private static final String MOVIE_BASE_URL = "http://www.omdbapi.com";

    @Value("${movie.api.key}")
    private String apiKey;
    @Autowired
    public MovieParamService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getMovieParamCharacteristics(String movieName) {
        String url = UriComponentsBuilder
                .fromUriString(MOVIE_BASE_URL)
                .queryParam("apikey", apiKey)
                .queryParam("t",movieName)
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }

}
