package com.example.CinemaApp.D_entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private int seatRows;
    private int seatColumns;
    private boolean status;
    private Double extraPrice;

    @OneToMany(mappedBy = "seat", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("seat - ticket")
    private List<Ticket> tickets;


    @ManyToOne
    @JoinColumn(name = "cinemaRoom_id")
    @JsonBackReference("cinemaRoom - seat")
    private CinemaRoom cinemaRoom;

    public Seat() {
    }
    public Seat(int seatRows, int seatColumns, String seats, boolean status, Double extraPrice) {
        this.seatRows = seatRows;
        this.seatColumns = seatColumns;
        this.status = true;
        this.extraPrice = extraPrice;
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

    public int getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(int setRows) {
        this.seatRows = setRows;
    }

    public int getSeatColumns() {
        return seatColumns;
    }

    public void setSeatColumns(int seatColumns) {
        this.seatColumns = seatColumns;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = true;
    }

    public Double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Double extraPrice) {
        this.extraPrice = extraPrice;
    }
}
