package server.repository;

import server.exception.repository.RepositoryException;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * @author Miroslav Kološnjaji
 */
public interface Repository <T, V>{

    void add(T t) throws RepositoryException;
    void update(T t) throws RepositoryException;
    void delete (T t) throws RepositoryException;
    List<T> getAll() throws RepositoryException;
    Optional<T> findById(V v) throws RepositoryException;
    default Queue<Object> createParamQueue(){
        return new ArrayDeque<>();
    }

}
