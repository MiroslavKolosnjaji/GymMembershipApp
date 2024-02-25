package backend.service;

import backend.domain.User;
import backend.exception.InvalidPasswordException;
import backend.exception.RepositoryException;
import backend.exception.UserNotFoundException;

import java.util.Optional;

/**
 * @author Miroslav Kološnjaji
 */
public interface UserService extends CRUDService<User, Long> {

    Optional<User> login(User user) throws UserNotFoundException, InvalidPasswordException, RepositoryException;
}
