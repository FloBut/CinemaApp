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
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(movieRequestDTO.getCinemaRoomId()).orElseThrow(() -> new RuntimeException("cinema room not found"));
        //imi construiesc filmul
        Movie movie = new Movie();
        //imi trebuie cinemaRoomul cu Id-ul mentionat in obiectul DTO

        // pentru film trebuie sa ii setez:
            //numele
            //pretul
            //id ul salii
            //lista de proiectii
        movie.setName(movieRequestDTO.getName());
        // Celelalte detalii ale filmului se vor popula printr-un request la un API,
        //adica sa ne aduca genul,descrierea si un rating
        //imi pun ca si coloane in movie aceste 3 entitati si apoi ar trebui sa aplez api extern
        //sa imi completez atributele acestor entitati cu datele oferite de api extern inainte sa salvez
        //filmul in baza de date

        //movie.setGenre(pasez ce am primit din api extern)
        //http://www.omdbapi.com/?t=Goodfellas
        //{"Title":"Goodfellas","Year":"1990","Rated":"R","Released":"21 Sep 1990","Runtime":"145 min","Genre":"Biography, Crime, Drama","Director":"Martin Scorsese","Writer":"Nicholas Pileggi, Martin Scorsese","Actors":"Robert De Niro, Ray Liotta, Joe Pesci","Plot":"The story of Henry Hill and his life in the mafia, covering his relationship with his wife Karen and his mob partners Jimmy Conway and Tommy DeVito.","Language":"English, Italian","Country":"United States","Awards":"Won 1 Oscar. 44 wins & 38 nominations total","Poster":"https://m.media-amazon.com/images/M/MV5BY2NkZjEzMDgtN2RjYy00YzM1LWI4ZmQtMjIwYjFjNmI3ZGEwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"8.7/10"},{"Source":"Rotten Tomatoes","Value":"94%"},{"Source":"Metacritic","Value":"92/100"}],"Metascore":"92","imdbRating":"8.7","imdbVotes":"1,236,150","imdbID":"tt0099685","Type":"movie","DVD":"15 Aug 2008","BoxOffice":"$46,909,721","Production":"N/A","Website":"N/A","Response":"True"}
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
        projection.setStartDate(projectionRequestDTO.getStartTime());
        projection.setEndDate(projectionRequestDTO.getEndTime());
        projection.setMovie(movie);
        projection.setCinemaRoom(cinemaRoom);
        return projection;
    }

}
