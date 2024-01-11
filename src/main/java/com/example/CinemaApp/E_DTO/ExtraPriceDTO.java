package com.example.CinemaApp.E_DTO;

public class ExtraPriceDTO {
    private Integer startingRow;
    private Integer endingRow;
    private Double extraPrice;

    public ExtraPriceDTO(Integer staringRow, Integer endingRow, Double extraPrice) {
        this.startingRow = staringRow;
        this.endingRow = endingRow;
        this.extraPrice = extraPrice;
    }

    public Integer getStartingRow() {
        return startingRow;
    }

    public void setStartingRow(Integer startingRow) {
        this.startingRow = startingRow;
    }

    public Integer getEndingRow() {
        return endingRow;
    }

    public void setEndingRow(Integer endingRow) {
        this.endingRow = endingRow;
    }

    public Double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Double extraPrice) {
        this.extraPrice = extraPrice;
    }
}
