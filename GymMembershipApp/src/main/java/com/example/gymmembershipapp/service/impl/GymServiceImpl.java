package com.example.gymmembershipapp.service.impl;

import com.example.gymmembershipapp.domain.Gym;
import com.example.gymmembershipapp.repository.Repository;
import com.example.gymmembershipapp.service.GymService;

import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class GymServiceImpl implements GymService {

    private Repository<Gym, Long> gymRepository;

    public GymServiceImpl(Repository<Gym, Long> gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Override
    public void add(Gym gym) throws Exception {
        gymRepository.add(gym);
    }

    @Override
    public void update(Gym gym) throws Exception {
        gymRepository.update(gym);
    }

    @Override
    public void delete(Gym gym) throws Exception {
        gymRepository.delete(gym);
    }

    @Override
    public List<Gym> getAll() throws Exception {
        return gymRepository.getAll();
    }
}
