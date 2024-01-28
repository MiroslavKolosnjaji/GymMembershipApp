package backend.repository.impl;

import backend.database.Database;
import backend.domain.City;
import backend.domain.User;
import backend.exception.DatabaseException;
import backend.exception.RepositoryException;
import backend.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class UserRepositoryImpl implements UserRepository {

    private final Database db = Database.getInstance();
    @Override
    public void add(User user) throws RepositoryException {
        try{
            String call = "CALL insert_user(?,?,?,?,?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setString(1, user.getFirstName());
            callableStatement.setString(2, user.getLastName());
            callableStatement.setString(3, user.getPhone());
            callableStatement.setString(4, user.getEmail());
            callableStatement.setLong(5, user.getCity().getCityId());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
        }catch (SQLException | DatabaseException exception){
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while adding user into database");
        }
    }

    @Override
    public void update(User user) throws RepositoryException {
        try{
            String call = "CALL update_user(?,?,?,?,?,?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setLong(1, user.getUserId());
            callableStatement.setString(2, user.getFirstName());
            callableStatement.setString(3, user.getLastName());
            callableStatement.setString(4, user.getPhone());
            callableStatement.setString(5, user.getEmail());
            callableStatement.setLong(6, user.getCity().getCityId());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
        }catch (SQLException | DatabaseException exception){
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while updating user in database");
        }
    }

    @Override
    public void delete(User user) throws RepositoryException {
        try{
            String call = "CALL delete_user(?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setLong(1, user.getUserId());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
        }catch (SQLException | DatabaseException exception){
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while deleting user in database");
        }
    }

    @Override
    public List<User> getAll() throws RepositoryException {
        try{
            List<User> userList = new ArrayList<>();

            String call = "CALL get_all_users()";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            ResultSet resultSet = callableStatement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Long cityId = resultSet.getLong("city_id");

                userList.add(new User(id,firstName,lastName,null, phone,email, password, new City(cityId)));
            }

            resultSet.close();
            callableStatement.close();
            db.confirmTransaction();
            return userList;

        }catch (SQLException | DatabaseException exception){
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
    public User findById(Long aLong) throws RepositoryException {
        return null;
    }

    @Override
    public List<User> login(User user) throws RepositoryException {
        try{
            List<User> users = new ArrayList<>();

            String query = "SELECT user_id, first_name, last_name, phone, email, password, city_id FROM `USER` WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = db.getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String password = resultSet.getString("email");
                Long cityId = resultSet.getLong("city_id");

                users.add(new User(id, firstName, lastName, null, phone, email, password, new City(cityId)));
            }

            resultSet.close();
            preparedStatement.close();
            db.confirmTransaction();
            return users;

        }catch (SQLException | DatabaseException exception){
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while searching for user in database");
        }
    }
}
