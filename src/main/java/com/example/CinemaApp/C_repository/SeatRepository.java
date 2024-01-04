package com.example.CinemaApp.C_repository;

import com.example.CinemaApp.D_entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    public Optional<Seat> findSeatBySeatRowsAndAndSeatColumnsAndAndCinemaRoom_Id(Integer row, Integer col, Long cinemaRoomId);
}
