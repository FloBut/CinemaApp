package com.example.CinemaApp.A_controller;

import com.example.CinemaApp.E_DTO.OrderCinemaRequestDTO;
import com.example.CinemaApp.D_entities.OrderCinema;
import com.example.CinemaApp.B_service.OrderCinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderCinema")
public class OrderCinemaController<MessagingException extends Throwable> {
    private OrderCinemaService orderCinemaService;
    @Autowired
    public OrderCinemaController(OrderCinemaService orderCinemaService) {
        this.orderCinemaService = orderCinemaService;
    }

    @PostMapping
    public ResponseEntity<OrderCinema> addMovie(@RequestBody OrderCinemaRequestDTO orderCinemaRequestDTO) {
        return ResponseEntity.ok(orderCinemaService.addOrderCinema(orderCinemaRequestDTO));
    }
}
