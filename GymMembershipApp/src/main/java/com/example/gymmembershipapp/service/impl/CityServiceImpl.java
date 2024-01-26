package com.example.gymmembershipapp.service.impl;

import com.example.gymmembershipapp.domain.City;
import com.example.gymmembershipapp.repository.Repository;
import com.example.gymmembershipapp.service.CityService;

import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class CityServiceImpl implements CityService {

    private final Repository<City, Long> cityRepository;

    public CityServiceImpl(Repository<City, Long> cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void add(City city) throws Exception {
        cityRepository.add(city);
    }

    @Override
    public void update(City city) throws Exception {
        cityRepository.update(city);
    }

    @Override
    public void delete(City city) throws Exception {
        cityRepository.delete(city);
    }

    @Override
    public List<City> getAll() throws Exception {
        return cityRepository.getAll();
    }
}
