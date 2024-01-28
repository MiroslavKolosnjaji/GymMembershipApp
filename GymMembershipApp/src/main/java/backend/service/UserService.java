package backend.service;

import backend.domain.User;

/**
 * @author Miroslav Kološnjaji
 */
public interface UserService extends CRUDService<User, Long> {

    User login(User user) throws Exception;
}
