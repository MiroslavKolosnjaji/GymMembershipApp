package com.example.gymmembershipapp.service.impl;

import com.example.gymmembershipapp.domain.User;
import com.example.gymmembershipapp.repository.Repository;
import com.example.gymmembershipapp.service.UserService;

import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class UserServiceImpl implements UserService {

    private final Repository<User, Long> userRepository;

    public UserServiceImpl(Repository<User, Long> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) throws Exception {
        userRepository.add(user);
    }

    @Override
    public void update(User user) throws Exception {
        userRepository.update(user);
    }

    @Override
    public void delete(User user) throws Exception {
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() throws Exception {
        return userRepository.getAll();
    }
}
