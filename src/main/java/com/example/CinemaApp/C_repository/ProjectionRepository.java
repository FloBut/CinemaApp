package com.example.CinemaApp.C_repository;

import com.example.CinemaApp.D_entities.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, Long> {
}
