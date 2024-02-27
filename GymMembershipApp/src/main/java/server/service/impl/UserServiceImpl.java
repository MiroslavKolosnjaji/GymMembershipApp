package server.service.impl;

import server.domain.User;
import server.exception.service.InvalidPasswordException;
import server.exception.repository.RepositoryException;
import server.exception.service.UserNotFoundException;
import server.repository.UserRepository;
import server.service.UserService;
import server.util.PasswordManager;

import java.util.List;
import java.util.Optional;

/**
 * @author Miroslav Kološnjaji
 */
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordManager passwordManager;

    public UserServiceImpl(UserRepository userRepository, PasswordManager passwordManager) {
        this.userRepository = userRepository;
        this.passwordManager = passwordManager;
    }

    @Override
    public void add(User user) throws Exception {
        String hashPassword = passwordManager.hashPassword(user.getPassword());
        user.setPassword(hashPassword);
        userRepository.add(user);

    }

    @Override
    public void update(User user) throws Exception {
        userRepository.update(user);
    }

    @Override
    public void delete(User user) throws Exception {
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() throws Exception {
        return userRepository.getAll();
    }

    @Override
    public Optional<User> login(User user) throws UserNotFoundException, InvalidPasswordException, RepositoryException {

        Optional<User> existingUser = userRepository.findByEmail(user);

        if (existingUser.isEmpty())
            throw new UserNotFoundException("User with this email address doesn't exist!");

        boolean verified = existingUser.map(u -> passwordManager.verifyPassword(user.getPassword(), u.getPassword())).orElse(false);

        if (!verified)
            throw new InvalidPasswordException("Wrong password! Try again!");

        return existingUser;
    }
}
