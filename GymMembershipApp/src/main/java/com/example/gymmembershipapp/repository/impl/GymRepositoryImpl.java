package com.example.gymmembershipapp.repository.impl;

import com.example.gymmembershipapp.database.Database;
import com.example.gymmembershipapp.domain.City;
import com.example.gymmembershipapp.domain.Contact;
import com.example.gymmembershipapp.domain.Gym;
import com.example.gymmembershipapp.exception.DatabaseException;
import com.example.gymmembershipapp.exception.RepositoryException;
import com.example.gymmembershipapp.repository.Repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class GymRepositoryImpl implements Repository<Gym, Long> {

    private final Database db = Database.getInstance();
    @Override
    public void add(Gym gym) throws RepositoryException {
        try{
            String call = "CALL insert_gym(?,?,?,?,?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setString(1, gym.getName());
            callableStatement.setString(2, gym.getAddress());
            callableStatement.setString(3, gym.getContact().getPhone());
            callableStatement.setString(4, gym.getContact().getEmail());
            callableStatement.setLong(5, gym.getCity().getCityId());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
        }catch (SQLException  | DatabaseException exception){
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while adding gym into database");
        }
    }

    @Override
    public void update(Gym gym) throws RepositoryException {
        try{
            String call = "CALL update_gym(?,?,?,?,?,?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setLong(1, gym.getGymId());
            callableStatement.setString(2, gym.getName());
            callableStatement.setString(3, gym.getAddress());
            callableStatement.setString(4, gym.getContact().getPhone());
            callableStatement.setString(5, gym.getContact().getEmail());
            callableStatement.setLong(6, gym.getCity().getCityId());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
        }catch (SQLException  | DatabaseException exception){
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while updating gym in database");
        }
    }

    @Override
    public void delete(Gym gym) throws RepositoryException {
        try{
            String call = "CALL delete_gym(?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setLong(1, gym.getGymId());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
            db.confirmTransaction();
        }catch (SQLException  | DatabaseException exception){
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while deleting gym from database");
        }
    }

    @Override
    public List<Gym> getAll() throws RepositoryException {
        try{
            List<Gym> gymList = new ArrayList<>();

            String call = "CALL get_all_gyms()";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            ResultSet resultSet = callableStatement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("gym_id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                Long cityId = resultSet.getLong("city_id");

                gymList.add(new Gym(id, name, new Contact(phone, email), address,  new City(cityId)));
            }

            resultSet.close();
            callableStatement.close();
            db.confirmTransaction();
            return gymList;

        }catch (SQLException  | DatabaseException exception){
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while loading gyms from database");
        }
    }

    @Override
    public Gym findById(Long aLong) throws RepositoryException {
        return null;
    }
}
