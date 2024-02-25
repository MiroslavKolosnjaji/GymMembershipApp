package backend.repository;

import backend.domain.User;
import backend.exception.RepositoryException;

import java.util.List;
import java.util.Optional;

/**
 * @author Miroslav Kološnjaji
 */
public interface UserRepository extends Repository<User, Long> {
    List<User> login(User user) throws RepositoryException;
    Optional<User> findByEmail(User user) throws RepositoryException;
}
