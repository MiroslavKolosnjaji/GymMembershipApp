package com.example.gymmembershipapp.repository.impl;

import com.example.gymmembershipapp.database.Database;
import com.example.gymmembershipapp.domain.City;
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
public class CityRepositoryImpl implements Repository<City, Long> {

    private Database db = Database.getInstance();


    @Override
    public void add(City city) throws RepositoryException {
        try {
            String call = "CALL insert_city(?,?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setString(1, city.getName());
            callableStatement.setString(2, city.getName());
            callableStatement.execute();

            callableStatement.close();
            db.confirmTransaction();
        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while adding new city to database");
        }
    }

    @Override
    public void update(City city) throws RepositoryException {
        try {
            String call = "CALL update_city(?, ?, ?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setLong(1, city.getCityId());
            callableStatement.setString(2, city.getName());
            callableStatement.setString(3, city.getZipCode());
            callableStatement.execute();

            callableStatement.close();
            db.confirmTransaction();
        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while updating data for city in database");
        }

    }

    @Override
    public void delete(City city) throws RepositoryException {
        try {
            String call = "CALL delete_city(?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setLong(1, city.getCityId());
            callableStatement.execute();

            callableStatement.close();
            db.confirmTransaction();

        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while deleting city from database");
        }
    }

    @Override
    public List<City> getAll() throws RepositoryException {

        try {
            List<City> cityList = new ArrayList<>();

            String call = "CALL get_all_cities()";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("city_id");
                String name = resultSet.getString("name");
                String zip_code = resultSet.getString("zip_code");

                cityList.add(new City(id, name, zip_code));
            }

            resultSet.close();
            callableStatement.close();
            db.confirmTransaction();
            return cityList;
        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while loading cites from database");
        }
    }

    @Override
    public City findById(Long aLong) throws RepositoryException {
        return null;
    }
}
