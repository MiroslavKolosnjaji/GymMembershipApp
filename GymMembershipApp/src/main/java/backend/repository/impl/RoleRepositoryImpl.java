package backend.repository.impl;

import backend.database.Database;
import backend.domain.Role;
import backend.exception.DatabaseException;
import backend.exception.RepositoryException;
import backend.repository.RoleRepository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Miroslav Kološnjaji
 */
public class RoleRepositoryImpl implements RoleRepository {

    private Database db = Database.getInstance();


    @Override
    public void add(Role role) throws RepositoryException {

        try {
            String call = "CALL insert_role(?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setString(1, role.getRole());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while adding role in database");
        }

    }

    @Override
    public void update(Role role) throws RepositoryException {
        try {
            String call = "CALL update_role(?,?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setLong(1, role.getRoleId());
            callableStatement.setString(2, role.getRole());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while updating role in database");
        }
    }

    @Override
    public void delete(Role role) throws RepositoryException {
        try{
            String call = "CALL delete_role(?)";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            callableStatement.setLong(1, role.getRoleId());
            callableStatement.executeUpdate();

            callableStatement.close();
            db.confirmTransaction();
        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while deleting role from database");
        }
    }

    @Override
    public List<Role> getAll() throws RepositoryException {
        try{
            List<Role> roleList = new ArrayList<>();

            String call = "CALL get_all_roles()";
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            ResultSet resultSet = callableStatement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("role_id");
                String role = resultSet.getString("role");

                roleList.add(new Role(id, role));
            }

            resultSet.close();
            callableStatement.close();
            db.confirmTransaction();
            return roleList;

        } catch (SQLException | DatabaseException exception) {
            try {
                db.cancelTransaction();
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }
            exception.printStackTrace();
            throw new RepositoryException("An error occured while loading roles from database");
        }
    }

    @Override
    public Optional<Role> findById(Long aLong) throws RepositoryException {
        return Optional.empty();
    }
}
