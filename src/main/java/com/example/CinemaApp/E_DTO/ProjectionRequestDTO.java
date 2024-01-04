package com.example.CinemaApp.E_DTO;

import java.time.LocalDateTime;

public class ProjectionRequestDTO {
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public ProjectionRequestDTO(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

}
