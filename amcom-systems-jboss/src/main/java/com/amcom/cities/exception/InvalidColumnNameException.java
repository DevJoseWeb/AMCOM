package com.amcom.cities.exception;

/**
 * Leitura e manutenção de cidades de um arquivo CSV feito com Java
 *
 * @author  Jose RF Junior
 * @version 1.0
 * @since   18/07/2018
 */
public class InvalidColumnNameException extends RuntimeException {

    private final String column;

    public InvalidColumnNameException(String column) {
        super();
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
