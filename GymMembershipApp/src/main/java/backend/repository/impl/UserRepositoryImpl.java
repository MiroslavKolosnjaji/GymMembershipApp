package backend.repository.impl;

import backend.database.Database;
import backend.domain.City;
import backend.domain.User;
import backend.exception.database.DatabaseException;
import backend.exception.repository.RepositoryException;
import backend.repository.UserRepository;
import backend.util.RepositoryUtility;

import java.sql.*;
import java.util.*;

/**
 * @author Miroslav Kološnjaji
 */
public class UserRepositoryImpl implements UserRepository {

    private final Database db = Database.getInstance();
    private final Queue<Object> paramQueue = createParamQueue();

    @Override
    public void add(User user) throws RepositoryException {
        try {
            String call = "CALL insert_user(?,?,?,?)";
            List<Object> list = Arrays.asList(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
            paramQueue.addAll(list);

            RepositoryUtility.executeUpdate(call, paramQueue );

        } catch ( DatabaseException exception) {

            exception.printStackTrace();
            throw new RepositoryException("An error occured while adding user into database");
        }
    }

    @Override
    public void update(User user) throws RepositoryException {
        try {
            if(user.getPhone() == null)
                user.setPhone("");
            if(user.getCity() == null)
                user.setCity(new City());

            String call = "CALL update_user(?,?,?,?,?,?)";
            List<Object> list = Arrays.asList(user.getUserId(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail(), user.getCity().getCityId());
            paramQueue.addAll(list);

            RepositoryUtility.executeUpdate(call, paramQueue);

        } catch (DatabaseException exception) {
            exception.printStackTrace();
            throw new RepositoryException("An error occured while updating user in database");
        }
    }

    @Override
    public void delete(User user) throws RepositoryException {
        try {
            String call = "CALL delete_user(?)";
            paramQueue.add(user.getUserId());
            RepositoryUtility.executeUpdate(call, paramQueue);

        } catch (DatabaseException exception) {
            exception.printStackTrace();
            throw new RepositoryException("An error occured while deleting user in database");
        }
    }

    @Override
    public List<User> getAll() throws RepositoryException {
        try {
            List<User> userList = new ArrayList<>();

            String call = "CALL get_all_users()";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Long cityId = resultSet.getLong("city_id");

                userList.add(new User(id, firstName, lastName, null, phone, email, password, new City(cityId)));
            }

            resultSet.close();
            callableStatement.close();
            db.confirmTransaction();
            return userList;

        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while loading users from database");
        }
    }

    @Override
    public Optional<User> findById(Long aLong) throws RepositoryException {
        return Optional.empty();
    }

    @Override
    public List<User> login(User user) throws RepositoryException {
        try {
            List<User> users = new ArrayList<>();

            String query = "SELECT user_id, first_name, last_name, phone, email, password, city_id FROM `USER` WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String password = resultSet.getString("password");
                Long cityId = resultSet.getLong("city_id");

                users.add(new User(id, firstName, lastName, null, phone, email, password, new City(cityId)));
            }

            resultSet.close();
            preparedStatement.close();
            db.confirmTransaction();
            return users;

        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while searching for user in database");
        }
    }

    @Override
    public Optional<User> findByEmail(User user) throws RepositoryException {

        try {

            List<Optional<User>> users = new ArrayList<>();

            String query = "SELECT user_id, first_name, last_name, phone, email, password, city_id FROM `USER` WHERE email = ?";
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String password = resultSet.getString("password");
                Long cityId = resultSet.getLong("city_id");
                users.add(Optional.of(new User(id, firstName, lastName, null, phone, email, password, new City(cityId))));
            }

            resultSet.close();
            preparedStatement.close();
            db.confirmTransaction();

            return users.getFirst();

        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }

            exception.printStackTrace();
            throw new RepositoryException("An error occured while searching user by email.");
        }
    }
}
