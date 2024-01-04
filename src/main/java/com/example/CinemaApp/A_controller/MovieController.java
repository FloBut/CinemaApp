package com.example.CinemaApp.A_controller;

import com.example.CinemaApp.E_DTO.MovieRequestDTO;
import com.example.CinemaApp.D_entities.Movie;
import com.example.CinemaApp.B_service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //Adaug un nou film, care se va difuza intr-o anumita sala.
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
        return ResponseEntity.ok((movieService.addMovie(movieRequestDTO)));
    }


}
