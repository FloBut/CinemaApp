package com.example.CinemaApp.B_service;

import com.example.CinemaApp.C_repository.ProjectionRepository;
import com.example.CinemaApp.C_repository.SeatRepository;
import com.example.CinemaApp.C_repository.TicketRepository;
import com.example.CinemaApp.D_entities.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatService {
    private SeatRepository seatRepository;
    private ProjectionRepository projectionRepository;
    private TicketRepository ticketRepository;
    @Autowired
    public SeatService(SeatRepository seatRepository, ProjectionRepository projectionRepository, TicketRepository ticketRepository) {
        this.seatRepository = seatRepository;
        this.projectionRepository = projectionRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public List<Seat> getAvailableSeats(Long projectionId) {
        // Projection projection = projectionRepository.findById(projectionId).orElseThrow(()-> new RuntimeException("projection not found"));
////        List<Seat> seats = new ArrayList<>();
////        //gasim toate siturile care nu au tickete pentru proiectia cu id ul meu
////        //de aici gasim toate ticketele proiectiei si de aici stim toate siturile ocupate
////        //filtram siturile neocupate
////
////        //luam ticketele pentru proiectia declarata mai sus
////        List<Ticket> projectionTickets = projection.getTickets();
////
////        //sa nu iau in 2 etape adica proiectia din baza de date si apoi ticketele proiectiei
////        // ma pot folosi de un repository de tikets si sa scot direct o lista de tickete
////        // pentru o proiectie
//        List<Ticket> ticketsFromProjection = ticketRepository.findAllByProjectionId(projectionId);
//        //pe lista obtinuta facem un stream in care vom retine in lista cu toate siturile care au tickete
//        List<Seat> occupiedSeat =  ticketsFromProjection.stream()
//                .map(ticket -> ticket.getSeat())
//                .collect(Collectors.toList());
//
//        //mi ar treebui toate locurile disponibile din cinema roomul proiectiei cu id ul dat
//       List<Seat> allSeats = seatRepository.findAllByCinemaRoom(projection.getCinemaRoom());
//       //din lista cu toate siturile o compar cu lista cu situri ocupate de mai sus
//        // si le iau doar pe cele care nu sunt in lista de locuri ocupate
//        List<Seat> availableSeats = allSeats.stream()
//                .filter(seat -> !occupiedSeat.contains(seat))
//                .collect(Collectors.toList());
//        //returnez lista cu locuri care sunt disponibile in sala de cinema

        return seatRepository.findAvailableSeatsBy(projectionId);

    }

    //cum verific daca un loc care are un id este liber?
        //un loc este liber daca la proiectia cu un anumit id nu are tiket
        //ma duc in ticket reprository si acolo gasesc tiketetul
        // pentru locul cu un id si proiectia cu un id
    public boolean isSeatAvailable (Long projectionId, Long seatId) {
        //pe ticketRepository apelez metoda care imi returneaza un tiket
            //daca tiketul nu are nici o valoare, e gol atunci locul este disponibil
        return ticketRepository.findByProjection_IdAndSeat_Id(projectionId,seatId).isEmpty();
    }
}
