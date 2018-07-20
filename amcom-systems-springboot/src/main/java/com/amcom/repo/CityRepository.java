package com.amcom.repo;

import java.util.List;

import com.amcom.model.City;
import org.springframework.data.repository.CrudRepository;

/**
 * Leitura e manutenção de cidades de um arquivo CSV feito com Java
 *
 * @author  Jose RF Junior
 * @version 1.0
 * @since   18/07/2018
 */
public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findAllById(int id);
    List<City> findByIbge(int ibge);
}
