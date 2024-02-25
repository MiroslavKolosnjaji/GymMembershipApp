package backend.repository;

import backend.exception.RepositoryException;

import java.util.List;
import java.util.Optional;

/**
 * @author Miroslav Kološnjaji
 */
public interface Repository <T, V>{

    void add(T t) throws RepositoryException;
    void update(T t) throws RepositoryException;
    void delete (T t) throws RepositoryException;
    List<T> getAll() throws RepositoryException;
    Optional<T> findById(V v) throws RepositoryException;
}
