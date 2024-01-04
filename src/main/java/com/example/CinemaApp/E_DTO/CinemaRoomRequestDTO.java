package com.example.CinemaApp.E_DTO;

import java.util.List;

public class CinemaRoomRequestDTO {
   private Integer seatRows;
    private Integer seatColumns;
    private String name;

    private List<ExtraPriceDTO> extraPrice;

    public CinemaRoomRequestDTO(Integer seatRows, Integer seatColumns, String name, List<ExtraPriceDTO> extraPrice) {
        this.seatRows = seatRows;
        this.seatColumns = seatColumns;
        this.name = name;
        this.extraPrice = extraPrice;
    }

    public Integer getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(Integer seatRows) {
        this.seatRows = seatRows;
    }

    public Integer getSeatColumns() {
        return seatColumns;
    }

    public void setSeatColumns(Integer seatColumns) {
        this.seatColumns = seatColumns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExtraPriceDTO> getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(List<ExtraPriceDTO> extraPrice) {
        this.extraPrice = extraPrice;
    }
}
