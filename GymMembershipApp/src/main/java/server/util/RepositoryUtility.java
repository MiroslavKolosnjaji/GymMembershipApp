package server.util;

import server.database.Database;
import server.exception.database.DatabaseException;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Queue;

/**
 * @author Miroslav Kološnjaji
 */
public class RepositoryUtility {

    private static Database db = Database.getInstance();

    public static void executeUpdate(String call, Queue<Object> params) throws DatabaseException {
        try {
            CallableStatement callableStatement = db.getConnection().prepareCall(call);
            setParameters(callableStatement, params);
            callableStatement.executeUpdate();
            callableStatement.close();
            db.confirmTransaction();
        } catch (SQLException | DatabaseException exception) {
            db.cancelTransaction();
            exception.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String call) throws SQLException {
        CallableStatement callableStatement = db.getConnection().prepareCall(call);
        ResultSet resultSet = callableStatement.executeQuery();
        return resultSet;
    }


    private static void setParameters(CallableStatement callableStatement, Queue<Object> params) throws SQLException {
        int i = 1;
        while (!params.isEmpty()) {
            callableStatement.setObject(i++, params.poll());
        }
    }
}
