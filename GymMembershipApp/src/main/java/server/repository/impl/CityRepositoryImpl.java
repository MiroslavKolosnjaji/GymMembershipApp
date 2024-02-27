package server.repository.impl;

import server.database.Database;
import server.domain.City;
import server.exception.database.DatabaseException;
import server.exception.repository.RepositoryException;
import server.repository.CityRepository;
import server.util.RepositoryUtility;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Miroslav Kološnjaji
 */
public class CityRepositoryImpl implements CityRepository {

    private final Database db = Database.getInstance();
    private final Queue<Object> paramQueue = createParamQueue();


    @Override
    public void add(City city) throws RepositoryException {
        try {
            String call = "CALL insert_city(?,?)";
            List<Object> list = List.of(city.getCityId(), city.getName());
            paramQueue.addAll(list);

            RepositoryUtility.executeUpdate(call, paramQueue);

        } catch (DatabaseException exception) {
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
            callableStatement.executeUpdate();

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
            callableStatement.executeUpdate();

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
    public Optional<City> findById(Long aLong) throws RepositoryException {
        return Optional.empty();
    }
}
