package backend.service;

import backend.domain.User;
import backend.exception.service.InvalidPasswordException;
import backend.exception.repository.RepositoryException;
import backend.exception.service.UserNotFoundException;

import java.util.Optional;

/**
 * @author Miroslav Kološnjaji
 */
public interface UserService extends CRUDService<User, Long> {

    Optional<User> login(User user) throws UserNotFoundException, InvalidPasswordException, RepositoryException;
}
