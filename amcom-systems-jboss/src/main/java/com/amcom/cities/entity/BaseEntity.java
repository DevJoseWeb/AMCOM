package com.amcom.cities.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Leitura e manutenção de cidades de um arquivo CSV feito com Java
 *
 * @author  Jose RF Junior
 * @version 1.0
 * @since   18/07/2018
 */
@MappedSuperclass
public class BaseEntity<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
