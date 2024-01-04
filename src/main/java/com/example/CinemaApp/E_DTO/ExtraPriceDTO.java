package com.example.CinemaApp.E_DTO;

public class ExtraPriceDTO {
    private Integer startRow;
    private Integer endRow;
    private Double extraPrice;

    public ExtraPriceDTO(Integer starRow, Integer endRow, Double extraPrice) {
        this.startRow = starRow;
        this.endRow = endRow;
        this.extraPrice = extraPrice;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer starRow) {
        this.startRow = starRow;
    }


    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Double extraPrice) {
        this.extraPrice = extraPrice;
    }
}
