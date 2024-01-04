package com.example.CinemaApp.B_service;

import com.example.CinemaApp.C_repository.ProjectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectionService {
    ProjectionRepository projectionRepository;
    @Autowired
    public ProjectionService(ProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }
}
