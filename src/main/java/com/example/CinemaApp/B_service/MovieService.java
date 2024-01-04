package com.example.CinemaApp.B_service;

import com.example.CinemaApp.E_DTO.MovieRequestDTO;
import com.example.CinemaApp.E_DTO.ProjectionRequestDTO;
import com.example.CinemaApp.D_entities.CinemaRoom;
import com.example.CinemaApp.D_entities.Movie;
import com.example.CinemaApp.D_entities.Projection;
import com.example.CinemaApp.C_repository.CinemaRoomRepository;
import com.example.CinemaApp.C_repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    MovieRepository movieRepository;
    //aici imi gasesc cinemaRoom - ul dupa Id (il am atribut in obiectul de movieDTO)
    CinemaRoomRepository cinemaRoomRepository;

    //imi injectez obiectele prin constructor
    @Autowired
    public MovieService(MovieRepository movieRepository, CinemaRoomRepository cinemaRoomRepository) {
        this.movieRepository = movieRepository;
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    //tre sa adug un film nou
    @Transactional
    public Movie addMovie(MovieRequestDTO movieRequestDTO) {
        //imi construiesc filmul
        Movie movie = new Movie();
        //imi trebuie cinemaRoomul cu Id-ul mentionat in obiectul DTO
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(movieRequestDTO.getCinemaRoomId()).orElseThrow(() -> new RuntimeException("cinema room not found"));
        // pentru film trebuie sa ii setez:
            //numele
            //pretul
            //id ul salii
            //lista de proiectii
        movie.setName(movieRequestDTO.getName());
        movie.setMoviePrice(movieRequestDTO.getMoviePrice());
        movie.setProjections(generateMovieProjections(movieRequestDTO.getProjectionRequestDTOs(),movie, cinemaRoom));
        return movieRepository.save(movie);
    }

    //imi generez o lista de proiectii pa baza unui obiect de tip ProjectionRequestDTO
    // a unui film si a unei sali de cinema
    @Transactional
    public List<Projection> generateMovieProjections(List<ProjectionRequestDTO> projectionRequestDTOS, Movie movie, CinemaRoom cinemaRoom) {
        // din lista de proiectii a obiectuluiDTO mi ar trebui cele care au filmul si cinemaRoom
        return projectionRequestDTOS.stream()
                .map(projectionRequestDTO -> mapFromDTOToProjection(projectionRequestDTO, movie, cinemaRoom))
                .collect(Collectors.toList());
    }
    // vreau mai intai o proiectie in care sa am data de inceput, de sfarsit, fimlul si cinemRoom
    @Transactional
    public Projection mapFromDTOToProjection(ProjectionRequestDTO projectionRequestDTO, Movie movie, CinemaRoom cinemaRoom) {
        Projection projection = new Projection();
        projection.setStartDate(projectionRequestDTO.getStartDate());
        projection.setEndDate(projectionRequestDTO.getEndDate());
        projection.setMovie(movie);
        projection.setCinemaRoom(cinemaRoom);
        return projection;
    }

}
