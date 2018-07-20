package com.amcom.model;

import java.io.Serializable;

/**
 * Representa um estado.
 *
 * @author Jose RF Junior
 *
 */
public class Estado implements Serializable {
    private static final long serialVersionUID = -2223881168003688140L;

    private String nome;
    private Long numeroCidades;
    
    public Estado(String nome, Long numeroCidades) {
        this.nome = nome;
        this.numeroCidades = numeroCidades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getNumeroCidades() {
        return numeroCidades;
    }

    public void setNumeroCidades(Long numeroCidades) {
        this.numeroCidades = numeroCidades;
    }

}
