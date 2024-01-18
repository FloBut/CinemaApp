package com.example.CinemaApp.A_controller;

import com.example.CinemaApp.B_service.MovieParamService;
import com.example.CinemaApp.E_DTO.MovieParamDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/movies")
public class MovieParamController {
   private MovieParamService movieParamService;

@Autowired
    public MovieParamController(MovieParamService movieParamService) {
        this.movieParamService = movieParamService;
    }
    @GetMapping("/{movieName}/characteristics")
    public ResponseEntity<MovieParamDTO> getMovieParamCharacteristics(@PathVariable String movieName) {
        try {
            return ResponseEntity.ok(movieParamService.getMovieParamCharacteristics(movieName));
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }


    }
}
