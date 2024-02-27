package server.service;

import server.domain.User;
import server.exception.service.InvalidPasswordException;
import server.exception.repository.RepositoryException;
import server.exception.service.UserNotFoundException;

import java.util.Optional;

/**
 * @author Miroslav Kološnjaji
 */
public interface UserService extends CRUDService<User, Long> {

    Optional<User> login(User user) throws UserNotFoundException, InvalidPasswordException, RepositoryException;
}
