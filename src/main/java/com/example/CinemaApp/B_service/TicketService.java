package com.example.CinemaApp.B_service;

import com.example.CinemaApp.D_entities.Ticket;
import com.example.CinemaApp.C_repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    //returnez pretul total al unui bilet
    @Transactional
    public Double computeTicketPrice(Ticket ticket) {
        return ticket.getSeat().getExtraPrice() + ticket.getProjection().getMovie().getMoviePrice();

    }

}
