package com.example.CinemaApp.B_service;

import com.example.CinemaApp.C_repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {
    public SeatRepository seatRepository;
    @Autowired

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
}
