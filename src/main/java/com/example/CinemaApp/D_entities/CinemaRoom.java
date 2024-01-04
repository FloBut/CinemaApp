package com.example.CinemaApp.D_entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CinemaRoom {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @OneToMany(mappedBy = "cinemaRoom",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true) //oare ar trebui All?
    @JsonManagedReference("seats-cinemarooms")
    List<Seat> seats;

    @OneToMany(mappedBy = "cinemaRoom",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("cinemarooms-projections")
    List<Projection> projections;

    public CinemaRoom() {
    }

    public CinemaRoom(String name) {
        this.name = name;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
