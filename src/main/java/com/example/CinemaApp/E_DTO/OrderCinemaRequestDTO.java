package com.example.CinemaApp.E_DTO;

import java.util.List;

public class OrderCinemaRequestDTO {
    private Long projectionID;

    private Long userId;

    private List<TicketRequestDTO> ticketRequestDTOs;

    public OrderCinemaRequestDTO(Long projectionID, Long userId, List<TicketRequestDTO> ticketRequestDTOs) {
        this.projectionID = projectionID;
        this.userId = userId;
        this.ticketRequestDTOs = ticketRequestDTOs;
    }

    public Long getProjectionID() {
        return projectionID;
    }

    public void setProjectionID(Long projectionID) {
        this.projectionID = projectionID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TicketRequestDTO> getTicketRequestDTOs() {
        return ticketRequestDTOs;
    }

    public void setTicketRequestDTOs(List<TicketRequestDTO> ticketRequestDTOs) {
        this.ticketRequestDTOs = ticketRequestDTOs;
    }
}
