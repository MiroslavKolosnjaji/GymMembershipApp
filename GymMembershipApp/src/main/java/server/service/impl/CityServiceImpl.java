package server.service.impl;

import server.domain.City;
import server.repository.CityRepository;
import server.service.CityService;

import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
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
