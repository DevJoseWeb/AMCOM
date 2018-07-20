package com.amcom.cities.service;

import com.amcom.cities.dao.CityDao;
import com.amcom.cities.dto.CitiesByStateDto;
import com.amcom.cities.utils.CsvUtils;
import com.amcom.cities.entity.City;
import com.amcom.cities.exception.InvalidColumnNameException;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Leitura e manutenção de cidades de um arquivo CSV feito com Java
 *
 * @author  Jose RF Junior
 * @version 1.0
 * @since   18/07/2018
 */
@Stateless
public class CityService extends CrudService<City, Long, CityDao> {

    public void importCsv(InputStream inputStream) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
            List<City> cities = buffer.lines().skip(1).map((String line) -> {
                String[] columns = line.split(",");
                City city = new City();
                city.setIbge(CsvUtils.getIntegerValue(columns[0]));
                city.setUf(CsvUtils.getStringValue(columns[1]));
                city.setName(CsvUtils.getStringValue(columns[2]));
                city.setCapital(CsvUtils.getBooleanValue(columns[3]));
                city.setLongitude(CsvUtils.getBigDecimalValue(columns[4]));
                city.setLatitude(CsvUtils.getBigDecimalValue(columns[5]));
                city.setNoAccentsName(CsvUtils.getStringValue(columns[6]));
                city.setAlternativenames(CsvUtils.getStringValue(columns[7]));
                city.setMicroRegion(CsvUtils.getStringValue(columns[8]));
                city.setMesoregion(CsvUtils.getStringValue(columns[9]));
                return city;
            }).collect(Collectors.toList());
            insert(cities);
        }
    }

    public List<City> getCapitals() {
        return dao.get().getCapitals();
    }

    public List<CitiesByStateDto> statesBiggerAndSmallerNumberOfCities() {
        List<CitiesByStateDto> citiesByStateDtos = dao.get().numberOfCitiesByState();
        Optional<CitiesByStateDto> stateMaxCityNumber = citiesByStateDtos.stream().max(Comparator.comparing(CitiesByStateDto::getNumber));
        Optional<CitiesByStateDto> stateMinCityNumber = citiesByStateDtos.stream().min(Comparator.comparing(CitiesByStateDto::getNumber));
        return Arrays.asList(stateMaxCityNumber.get(), stateMinCityNumber.get());
    }

    public List<CitiesByStateDto> numberOfCitiesByState() {
        return dao.get().numberOfCitiesByState();
    }

    public City findCityByIbge(Integer ibge) {
        return dao.get().findCityByIbge(ibge);
    }

    public List<City> findCitiesByState(String state) {
        return dao.get().findCitiesByState(state);
    }

    public List<City> findCitiesByCsvColumn(String column, String filter) {
        String attribute = CsvUtils.RELATION_CSV_ENTITY.get(column);
        if (attribute == null) {
            throw new InvalidColumnNameException(column);
        }
        return dao.get().findCitiesByAttribute(attribute, filter);
    }

    public Long countRegisterByColumn(String column) {
        String attribute = CsvUtils.RELATION_CSV_ENTITY.get(column);
        if (attribute == null) {
            throw new InvalidColumnNameException(column);
        }
        return dao.get().countRegisterByAttribute(attribute);
    }
}
