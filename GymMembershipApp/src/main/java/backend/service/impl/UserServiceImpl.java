package backend.service.impl;

import backend.domain.User;
import backend.repository.UserRepository;
import backend.service.UserService;

import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) throws Exception {
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
    public User login(User user) throws Exception {

        List<User> users = userRepository.login(user);

        if (users.isEmpty())
            throw new Exception("User doesn't exist!");

        return users.get(0);
    }
}
