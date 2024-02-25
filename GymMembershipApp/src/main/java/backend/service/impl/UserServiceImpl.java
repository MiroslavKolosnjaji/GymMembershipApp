package backend.service.impl;

import backend.domain.User;
import backend.exception.InvalidPasswordException;
import backend.exception.RepositoryException;
import backend.exception.UserNotFoundException;
import backend.repository.UserRepository;
import backend.service.UserService;
import backend.util.PasswordManager;

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
        System.out.println(hashPassword);
        System.out.println("Duzina: " + hashPassword.length());
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
