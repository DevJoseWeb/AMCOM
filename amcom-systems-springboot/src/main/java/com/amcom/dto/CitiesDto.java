package com.amcom.dto;

/**
 * Leitura e manutenção de cidades de um arquivo CSV feito com Java
 *
 * @author  Jose RF Junior
 * @version 1.0
 * @since   18/07/2018
 */
public class CitiesDto {

    private String state;
    private Long number;

    public CitiesDto() {
    }

    public CitiesDto(String state, Long number) {
        this.state = state;
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
