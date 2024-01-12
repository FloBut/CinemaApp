package com.example.CinemaApp.A_controller;

import com.example.CinemaApp.B_service.MovieParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieParamController {
   private MovieParamService movieParamService;

@Autowired
    public MovieParamController(MovieParamService movieParamService) {
        this.movieParamService = movieParamService;
    }
    @GetMapping("/{movieName}/characteristics")
    public ResponseEntity<String> getCharacteristics(@RequestParam("movieName") String movieName) {
        return ResponseEntity.ok(movieParamService.getMovieParamCharacteristics(movieName));
    }
}
