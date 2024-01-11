package com.example.CinemaApp.E_DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieRequestDTO {
    // in acest obiect voi pune ce am in clasa Movie si in plus
    //si ID-ul salii de cinema
    String name;
    private Double moviePrice;
    private Long cinemaRoomId;
@JsonProperty("dates")
    List<ProjectionRequestDTO> projectionRequestDTOs;

    public MovieRequestDTO(String name, Long cinemaRoomId, Double moviePrice, List<ProjectionRequestDTO> projectionRequestDTOs) {
        this.name = name;
        this.cinemaRoomId = cinemaRoomId;
        this.moviePrice = moviePrice;
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
