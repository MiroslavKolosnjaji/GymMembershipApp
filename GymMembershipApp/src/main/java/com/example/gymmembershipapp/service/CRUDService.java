package com.example.gymmembershipapp.service;

import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public interface CRUDService<T, V>{

    void add(T t) throws Exception;
    void update(T t) throws Exception;
    void delete(T t ) throws Exception;
    List<T> getAll() throws Exception;
}
