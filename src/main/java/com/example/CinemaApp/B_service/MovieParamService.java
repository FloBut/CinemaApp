package com.example.CinemaApp.B_service;

import com.example.CinemaApp.E_DTO.MovieParamDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private ObjectMapper objectMapper;
    private static final String MOVIE_BASE_URL = "http://www.omdbapi.com";
    @Value("${movie.api.key}")
    private String apiKey;
    @Autowired
    public MovieParamService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    public MovieParamDTO getMovieParamCharacteristics(String movieName) throws JsonProcessingException {
        String url = UriComponentsBuilder
                .fromUriString(MOVIE_BASE_URL)
                .queryParam("apikey", apiKey)
                .queryParam("t",movieName)
                .toUriString();
        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);
        return mapFromJsonToMovieParamDTO(root);

    }

    //din json imi trebuie un obiect cu atrbutele din MovieParamDTO
    //    movieName din Json = movieName din movieParamDTO;
    //    rated  din Json = rated din movieParamDTO ;
    //    genre din JSON = genre din movieparamDTO;
    //    ratings din JSON = ratings din movieparamDTO
    public MovieParamDTO mapFromJsonToMovieParamDTO(JsonNode root) {
       String movieName =  root.path("Title").toString();
       String rated = root.path("Rated").toString();
       String genre = root.path("Genre").toString();
       String ratings = root.path("Ratings").toString();
       return new MovieParamDTO(movieName, rated, genre, ratings);
    }

}
