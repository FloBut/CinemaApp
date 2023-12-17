package com.example.CinemaApp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CinemaRoom {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;
    @OneToMany(mappedBy = "cinemaRoom", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("cinemaRoom - seat")
    private List<Seat> seats;
    @OneToMany(mappedBy = "cinemaRoom", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("cinemaRoom - projection")
    private List<Projection> projections;

    public CinemaRoom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Projection> getProjections() {
        return projections;
    }

    public void setProjections(List<Projection> projections) {
        this.projections = projections;
    }
}
