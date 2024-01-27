package com.example.gymmembershipapp.repository;

import com.example.gymmembershipapp.domain.User;
import com.example.gymmembershipapp.exception.RepositoryException;
import com.example.gymmembershipapp.repository.Repository;

import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public interface UserRepository extends Repository<User, Long> {
    List<User> login(User user) throws RepositoryException;
}
