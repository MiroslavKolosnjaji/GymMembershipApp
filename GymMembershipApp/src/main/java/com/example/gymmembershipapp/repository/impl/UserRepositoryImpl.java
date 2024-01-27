package com.example.gymmembershipapp.repository.impl;

import com.example.gymmembershipapp.database.Database;
import com.example.gymmembershipapp.domain.City;
import com.example.gymmembershipapp.domain.Contact;
import com.example.gymmembershipapp.domain.Person;
import com.example.gymmembershipapp.domain.User;
import com.example.gymmembershipapp.exception.DatabaseException;
import com.example.gymmembershipapp.exception.RepositoryException;
import com.example.gymmembershipapp.repository.Repository;
import com.example.gymmembershipapp.repository.UserRepository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            callableStatement.setString(1, user.getUser().getFirstName());
            callableStatement.setString(2, user.getUser().getLastName());
            callableStatement.setString(3, user.getUser().getContact().getPhone());
            callableStatement.setString(4, user.getUser().getContact().getEmail());
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
            callableStatement.setString(2, user.getUser().getFirstName());
            callableStatement.setString(3, user.getUser().getLastName());
            callableStatement.setString(4, user.getUser().getContact().getPhone());
            callableStatement.setString(5, user.getUser().getContact().getEmail());
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

                userList.add(new User(id,new Person(firstName,lastName, new Contact(phone,email)), password, new City(cityId)));
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
}
