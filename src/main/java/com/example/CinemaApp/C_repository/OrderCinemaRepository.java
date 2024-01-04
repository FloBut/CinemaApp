package com.example.CinemaApp.C_repository;

import com.example.CinemaApp.D_entities.OrderCinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCinemaRepository extends JpaRepository<OrderCinema, Long> {
}
