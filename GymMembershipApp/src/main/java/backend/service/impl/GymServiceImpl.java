package backend.service.impl;

import backend.domain.Gym;
import backend.repository.GymRepository;
import backend.service.GymService;

import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class GymServiceImpl implements GymService {

    private GymRepository gymRepository;

    public GymServiceImpl(GymRepository gymRepository) {
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
