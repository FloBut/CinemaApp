package com.example.CinemaApp.A_controller;

import com.example.CinemaApp.B_service.ProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projection")
public class ProjectionController {
    private ProjectionService projectionService;
    @Autowired
    public ProjectionController(ProjectionService projectionService) {
        this.projectionService = projectionService;
    }
}
