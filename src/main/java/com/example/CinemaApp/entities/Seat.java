package com.example.CinemaApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(mappedBy = "seat", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("seat - ticket")
    private List<Ticket> tickets;


    @ManyToOne
    @JoinColumn(name = "cinemaRoom_id")
    @JsonBackReference("cinemaRoom - seat")
    private CinemaRoom cinemaRoom;

    private int setRows;
    private int seatColumns;
    private String seats;
    private boolean status;


    public Seat() {
    }
    public Seat(int setRows, int seatColumns, String seats, boolean status) {
        this.setRows = setRows;
        this.seatColumns = seatColumns;
        this.seats = String.valueOf(List.of(setRows + "of" + seatColumns));
        this.status = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    public int getSetRows() {
        return setRows;
    }

    public void setSetRows(int setRows) {
        this.setRows = setRows;
    }

    public int getSeatColumns() {
        return seatColumns;
    }

    public void setSeatColumns(int seatColumns) {
        this.seatColumns = seatColumns;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
