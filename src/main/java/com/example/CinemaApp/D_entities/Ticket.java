package com.example.CinemaApp.D_entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference("orderCinema - ticket")
    private OrderCinema orderCinema;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    @JsonBackReference("seat - ticket")
    private Seat seat;
    @ManyToOne
    @JoinColumn(name = "projection_id")
    @JsonBackReference("projection - ticket")
    private Projection projection;

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderCinema getOrderCinema() {
        return orderCinema;
    }

    public void setOrderCinema(OrderCinema orderCinema) {
        this.orderCinema = orderCinema;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Projection getProjection() {
        return projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }
}
