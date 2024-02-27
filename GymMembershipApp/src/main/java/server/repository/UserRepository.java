package server.repository;

import server.domain.User;
import server.exception.repository.RepositoryException;

import java.util.List;
import java.util.Optional;

/**
 * @author Miroslav Kološnjaji
 */
public interface UserRepository extends Repository<User, Long> {
    List<User> login(User user) throws RepositoryException;
    Optional<User> findByEmail(User user) throws RepositoryException;
}
