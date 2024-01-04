package com.example.CinemaApp.A_controller;

import com.example.CinemaApp.B_service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SeatController {
    public SeatService seatService;
    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }
}
