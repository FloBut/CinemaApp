package com.example.CinemaApp.E_DTO;

import java.util.List;

public class MovieRequestDTO {
    // in acest obiect voi pune ce am in clasa Movie si in plus
    //si ID-ul salii de cinema
    String name;
    private Double moviePrice;
    private Long cinemaRoomId;

    List<ProjectionRequestDTO> projectionRequestDTOs;

    public MovieRequestDTO(String name, Double moviePrice, Long cinemaRoomId, List<ProjectionRequestDTO> projectionRequestDTOs ) {
        this.name = name;
        this.moviePrice = moviePrice;
        this.cinemaRoomId = cinemaRoomId;
        this.projectionRequestDTOs = projectionRequestDTOs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoviePrice() {
        return moviePrice;
    }

    public void setMoviePrice(Double moviePrice) {
        this.moviePrice = moviePrice;
    }

    public Long getCinemaRoomId() {
        return cinemaRoomId;
    }

    public void setCinemaRoomId(Long cinemaRoomId) {
        this.cinemaRoomId = cinemaRoomId;
    }

    public List<ProjectionRequestDTO> getProjectionRequestDTOs() {
        return projectionRequestDTOs;
    }

    public void setProjectionRequestDTOs(List<ProjectionRequestDTO> projectionRequestDTOs) {
        this.projectionRequestDTOs = projectionRequestDTOs;
    }
}
