package com.example.gymmembershipapp.repository;

import com.example.gymmembershipapp.database.Database;
import com.example.gymmembershipapp.exception.DatabaseException;
import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * @author Miroslav Kološnjaji
 */
public class RepositoryUtility {

    private static Database db = Database.getInstance();

    public static void executeUpdate(String call, Object[] params) throws DatabaseException {
        try {
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            setParameters(callableStatement, params);
            callableStatement.executeUpdate();
            db.confirmTransaction();
        } catch (SQLException | DatabaseException exception){
            db.cancelTransaction();
            exception.printStackTrace();
        }
    }

    private static void setParameters(CallableStatement callableStatement, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            callableStatement.setObject(i + 1, params[i]);
        }
    }
}
