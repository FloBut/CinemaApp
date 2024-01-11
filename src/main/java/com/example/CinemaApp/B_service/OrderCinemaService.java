package com.example.CinemaApp.B_service;

import com.example.CinemaApp.C_repository.OrderCinemaRepository;
import com.example.CinemaApp.C_repository.ProjectionRepository;
import com.example.CinemaApp.C_repository.SeatRepository;
import com.example.CinemaApp.C_repository.UserRepository;
import com.example.CinemaApp.D_entities.*;
import com.example.CinemaApp.E_DTO.OrderCinemaRequestDTO;
import com.example.CinemaApp.E_DTO.TicketRequestDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderCinemaService {
    private OrderCinemaRepository orderCinemaRepository;

    private UserRepository userRepository;

    private PdfGenerationService pdfGenerationService;

    private ProjectionRepository projectionRepository;

    private SeatRepository seatRepository;

    private SeatService seatService;

    private TicketService ticketService;

    private EmailService emailService;
@Autowired
    public OrderCinemaService(OrderCinemaRepository orderCinemaRepository, UserRepository userRepository, PdfGenerationService pdfGenerationService, ProjectionRepository projectionRepository, SeatRepository seatRepository, SeatService seatService, TicketService ticketService, EmailService emailService) {
        this.orderCinemaRepository = orderCinemaRepository;
        this.userRepository = userRepository;
        this.pdfGenerationService = pdfGenerationService;
        this.projectionRepository = projectionRepository;
        this.seatRepository = seatRepository;
        this.seatService = seatService;
        this.ticketService = ticketService;
        this.emailService = emailService;
    }

    public OrderCinemaRepository getOrderCinemaRepository() {
        return orderCinemaRepository;
    }

    public void setOrderCinemaRepository(OrderCinemaRepository orderCinemaRepository) {
        this.orderCinemaRepository = orderCinemaRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PdfGenerationService getPdfGenerationService() {
        return pdfGenerationService;
    }

    public void setPdfGenerationService(PdfGenerationService pdfGenerationService) {
        this.pdfGenerationService = pdfGenerationService;
    }

    public ProjectionRepository getProjectionRepository() {
        return projectionRepository;
    }

    public void setProjectionRepository(ProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }

    public SeatRepository getSeatRepository() {
        return seatRepository;
    }

    public void setSeatRepository(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public SeatService getSeatService() {
        return seatService;
    }

    public void setSeatService(SeatService seatService) {
        this.seatService = seatService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    //vreau sa adaug o comanda pentru o proiectie aunui film
        //deci am nevoie de un ticket si de un loc sau mai multe
    @Transactional
    public OrderCinema addOrderCinema(OrderCinemaRequestDTO orderCinemaRequestDTO) throws MessagingException {
        //cautam proiectia dupa iD
        //cautam user ul dupa Id
        User user = userRepository.findById(orderCinemaRequestDTO.getUserId()).orElseThrow(() -> new RuntimeException("user  not found"));
        Projection projection = projectionRepository.findById(orderCinemaRequestDTO.getProjectionID()).orElseThrow(() -> new RuntimeException("projection not found"));
        //imi creez o noua comanda
        OrderCinema orderCinema = new OrderCinema();
        //imi setez userul
        orderCinema.setUser(user);
        //ar trebui sa calculez pretul total pentru tiketele emise pentru aceasta comanda
        //dar mai intai ar ttrebui sa am tiketele in functie de proiectie

        orderCinema.setTotalPrice(computeTotalPrice(orderCinema.getTickets()));
        orderCinema.setTickets(generateOrderTicktes(orderCinema, projection, orderCinemaRequestDTO.getTicketRequestDTOs()));
        OrderCinema savedOrderCinema = orderCinemaRepository.save(orderCinema);
        pdfGenerationService.generatePdf("ai cumparat bilet la filmul " + projection.getMovie().getName(), "src/main/resources/order.pdf");
        emailService.sendMessageWithAttachment(user.getName(), "bilete", "ai luat bilete", "src/main/resources/order.pdf");
        return savedOrderCinema;
    }
    @Transactional
    public List<Ticket> generateOrderTicktes(OrderCinema orderCinema, Projection projection, List<TicketRequestDTO> ticketRequestDTOs) {
        return ticketRequestDTOs.stream()
                .map(ticketRequestDTO -> mapFromDTOToTicket(ticketRequestDTO, orderCinema , projection))
                .collect(Collectors.toList());

    }
    @Transactional
    public Ticket mapFromDTOToTicket(TicketRequestDTO ticketRequestDTO, OrderCinema orderCinema, Projection projection) {
        Ticket ticket = new Ticket();
        ticket.setOrderCinema(orderCinema);
        ticket.setProjection(projection);
        Seat seat = seatRepository.findSeatBySeatRowsAndAndSeatColumnsAndAndCinemaRoom_Id(ticketRequestDTO.getRow(), ticketRequestDTO.getCol(), projection.getCinemaRoom().getId()).orElseThrow(()->new RuntimeException("seat not found"));
        if (!seatService.isSeatAvailable(projection.getId(), seat.getId())){
            throw new RuntimeException("seat not available");
        }
        ticket.setSeat(seat);
        return ticket;
    }
    @Transactional
    public Double computeTotalPrice(List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> ticketService.computeTicketPrice(ticket))
                .reduce((sum, ticketPrice) -> sum+ticketPrice)
                .orElseThrow(()-> new RuntimeException("total price could not be computed"));
    }
}
