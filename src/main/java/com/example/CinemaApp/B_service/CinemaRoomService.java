package com.example.CinemaApp.B_service;

import com.example.CinemaApp.E_DTO.CinemaRoomRequestDTO;
import com.example.CinemaApp.E_DTO.ExtraPriceDTO;
import com.example.CinemaApp.D_entities.CinemaRoom;
import com.example.CinemaApp.D_entities.Seat;
import com.example.CinemaApp.C_repository.CinemaRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaRoomService {
    private CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    public CinemaRoomService(CinemaRoomRepository cinemaRoomRepository) {
        this.cinemaRoomRepository = cinemaRoomRepository;
    }
    @Transactional
    public CinemaRoom addCinemaRoom(CinemaRoomRequestDTO cinemaRoomRequestDTO) {
        //imi construiesc o sala de cinema
        CinemaRoom cinemaRoom = new CinemaRoom();
        //setez numele cinemaroomului in functie de numele obiectului dto
        cinemaRoom.setName(cinemaRoomRequestDTO.getName());
        //setez locurile din cinemaroom
        cinemaRoom.setSeats(generateSeatsForCinemaRoom(cinemaRoomRequestDTO, cinemaRoom));
        //cu ajutorul beenului de cinemaRoomRepository care face operatii crud
        // voi salva cinemaRoomul in baza de date
        return cinemaRoomRepository.save(cinemaRoom);
    }

    // imi generez lista de locuri din sala de cinema cu ajutorul metodei
    @Transactional
    public List<Seat> generateSeatsForCinemaRoom(CinemaRoomRequestDTO cinemaRoomRequestDTO, CinemaRoom cinemaRoom) {
        //obiectul dto are un numar de randuri si un numar de coloane din
        // din care il voi scoate si pentru fiecare rand si pentru fiecare coloana
        //imi construiesc un loc
        //dupa ce mi am construit un loc imi fac o lista de locuri si
        //aduag fiecare loc in lista
        List<Seat> result = new ArrayList<>();
        for (int i = 1; i <= cinemaRoomRequestDTO.getSeatRows(); i++) {
            for (int j = 1; j <= cinemaRoomRequestDTO.getSeatColumns(); j++) {
                //imi crez un loc gol pentru care voi seta
                //disponibilitatea
                //extraprice
                //numele salii de cinema
                //randul
                //coloana
                Seat seat = new Seat();
                seat.setSeatRows(i);
                seat.setSeatColumns(j);
                seat.setCinemaRoom(cinemaRoom);
                //extraprice a fost setat pentru randuri de aceea aleg in functie de i
                //si ma folosesc de o metoda in care voi seta extraprice -ul
                seat.setExtraPrice(getExtraPrice(i, cinemaRoomRequestDTO.getExtraPrice()));
                result.add(seat);

            }
        }
        return result;
    }


    // pentru a obtine extra price-ul am nevoie de o metoda in care stiu numarul de randuri
        // si o lista de preturi pentru acele randuri lista de preturi

    @Transactional
    public Double getExtraPrice(Integer row, List<ExtraPriceDTO> extraPriceDTOS) {
        /* for (ExtraPriceDTO extraPriceDTO: extraPriceDTOS) {
                if (row > extraPriceDTO.getStartRow() && row < extraPriceDTO.getEndRow()) {
                    return extraPriceDTO.getExtraPrice();
                }
           }
         */
         Optional <Double> extraPriceOptional = extraPriceDTOS.stream()
                .filter(extraPriceDTO -> row >= extraPriceDTO.getStartingRow()
                        && row <= extraPriceDTO.getEndingRow())
                .map(extraPriceDTO -> extraPriceDTO.getExtraPrice())
                .findFirst();
         return extraPriceOptional.orElse(0.0);
    }

}




