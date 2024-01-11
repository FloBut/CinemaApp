package com.example.CinemaApp.A_controller;


import com.example.CinemaApp.B_service.OrderCinemaService;
import com.example.CinemaApp.D_entities.OrderCinema;
import com.example.CinemaApp.E_DTO.OrderCinemaRequestDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/orderCinema")
public class OrderCinemaController {
    private OrderCinemaService orderCinemaService;
    @Autowired
    public OrderCinemaController(OrderCinemaService orderCinemaService) {
        this.orderCinemaService = orderCinemaService;
    }

    @PostMapping
    public ResponseEntity<OrderCinema> addOrderCinema(@RequestBody OrderCinemaRequestDTO orderCinemaRequestDTO) {
        try {
            return ResponseEntity.ok(orderCinemaService.addOrderCinema(orderCinemaRequestDTO));
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
