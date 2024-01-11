package com.example.CinemaApp.C_repository;

import com.example.CinemaApp.D_entities.CinemaRoom;
import com.example.CinemaApp.D_entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    public List<Seat> findAllByCinemaRoom(CinemaRoom cinemaRoom);

    @Query("SELECT s FROM Seat s LEFT JOIN Ticket t ON s = t.seat AND t.projection.id = :projectionId WHERE t.id IS NULL")
    List<Seat> findAvailableSeatsBy(@Param("projectionId") Long projectionId);



    public Optional<Seat> findSeatBySeatRowsAndAndSeatColumnsAndAndCinemaRoom_Id(Integer row, Integer col, Long cinemaRoomId);
}
