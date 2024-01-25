package com.example.gymmembershipapp.repository;

import com.example.gymmembershipapp.exception.RepositoryException;

import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public interface Repository <T, V>{

    void add(T t) throws RepositoryException;
    void update(T t) throws RepositoryException;
    void delete (T t) throws RepositoryException;
    List<T> getAll() throws RepositoryException;
}
