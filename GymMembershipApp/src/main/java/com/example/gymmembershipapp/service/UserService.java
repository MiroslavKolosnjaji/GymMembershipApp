package com.example.gymmembershipapp.service;

import com.example.gymmembershipapp.domain.User;

/**
 * @author Miroslav Kološnjaji
 */
public interface UserService extends CRUDService<User, Long> {

    User login(User user) throws Exception;
}
