package com.example.CinemaApp.C_repository;

import com.example.CinemaApp.D_entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByProjection_IdAndSeat_Id(Long projectionId, Long seatId);

    List<Ticket> findAllByProjectionId(Long projectionId);
}
