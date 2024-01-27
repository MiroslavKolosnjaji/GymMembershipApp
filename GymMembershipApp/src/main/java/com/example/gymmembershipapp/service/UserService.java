package com.example.gymmembershipapp.service;

import com.example.gymmembershipapp.domain.User;

/**
 * @author Miroslav Kološnjaji
 */
public interface UserService extends CRUDService<User, Long> {

    void login(User user) throws Exception;
}
