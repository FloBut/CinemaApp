package com.example.CinemaApp.A_controller;

import com.example.CinemaApp.E_DTO.CinemaRoomRequestDTO;
import com.example.CinemaApp.D_entities.CinemaRoom;
import com.example.CinemaApp.B_service.CinemaRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CinemaRoom")
public class CinemaRoomController {
    // imi creez un obiect de tip CinemaService pentru a putea apela
        // metoda de adaugare a unei sali de cinema
    CinemaRoomService cinemaRoomService;
    //il injectez prin constructor
@Autowired
    public CinemaRoomController(CinemaRoomService cinemaRoomService) {
        this.cinemaRoomService = cinemaRoomService;
    }
    //Verific daca s-a adaugat o noua sala de cinema folosindu ma de beenu de service
    @PostMapping
    ResponseEntity<CinemaRoom> addCinemaRoom(@RequestBody CinemaRoomRequestDTO cinemaRoomRequestDTO) {
        return ResponseEntity.ok(cinemaRoomService.addCinemaRoom(cinemaRoomRequestDTO));
    }


}
