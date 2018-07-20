package com.amcom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.amcom.model.City;
import com.amcom.repo.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Leitura e manutenção de cidades de um arquivo CSV feito com Java
 *
 * @author  Jose RF Junior
 * @version 1.0
 * @since   18/07/2018
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    CityRepository cityRepository;

    @GetMapping("/citys")
    public List<City> getAllCitys(){
        List<City> citys = new ArrayList<>();
        cityRepository.findAll().forEach(citys::add);
        return citys;
    }

    @PostMapping(value = "/citys/create")
    public City postCity(@RequestBody City city){
           City _city = cityRepository.save(new City(city.getName()));
        return  _city;
    }

    @DeleteMapping("/citys/{id}")
    public ResponseEntity<String> deleteCitys(@PathVariable("id") long id) {

        cityRepository.deleteById(id);

        return new ResponseEntity<>("City has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/citys/delete")
    public ResponseEntity<String> deleteAllCitys() {

        cityRepository.deleteAll();

        return new ResponseEntity<>("All Citys have been deleted!", HttpStatus.OK);
    }

    @GetMapping(value = "citys/ibge/{ibge}")
    public List<City> findByAge(@PathVariable int ibge) {

        List<City> Citys = cityRepository.findByIbge(ibge);
        return Citys;
    }

    @PutMapping("/citys/{id}")
    public ResponseEntity<City> updateCustomer(@PathVariable("id") long id, @RequestBody City city) {

        Optional<City> customerData = cityRepository.findById(id);

        if (customerData.isPresent()) {
            City _city = customerData.get();
            _city.setName(city.getName());

            return new ResponseEntity<>(cityRepository.save(_city), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
