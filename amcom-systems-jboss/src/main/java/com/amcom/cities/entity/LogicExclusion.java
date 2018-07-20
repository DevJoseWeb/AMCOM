package com.amcom.cities.entity;

/**
 * Leitura e manutenção de cidades de um arquivo CSV feito com Java
 *
 * @author  Jose RF Junior
 * @version 1.0
 * @since   18/07/2018
 */
public interface LogicExclusion {

    void setExcluded(boolean excluded);

    boolean isExcluded();

}
