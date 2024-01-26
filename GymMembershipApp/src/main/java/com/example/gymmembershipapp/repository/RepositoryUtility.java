package com.example.gymmembershipapp.repository;

import com.example.gymmembershipapp.database.Database;
import com.example.gymmembershipapp.exception.DatabaseException;
import com.example.gymmembershipapp.exception.RepositoryException;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Miroslav Kološnjaji
 */
public class RepositoryUtility {

    private static Database db = Database.getInstance();

    public static void executeUpdate(String call, List<Object> param) throws DatabaseException {
        try {
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            setParameters(callableStatement, param);
            callableStatement.executeUpdate();
            db.confirmTransaction();
        } catch (SQLException | DatabaseException exception){
            db.cancelTransaction();
            exception.printStackTrace();
        }
    }

    private static void setParameters(CallableStatement callableStatement, List<Object> param) throws SQLException {
        for (int i = 0; i < param.size(); i++) {
            callableStatement.setObject(i + 1, param.get(i));
        }
    }
}
