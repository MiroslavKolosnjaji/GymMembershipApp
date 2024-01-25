package com.example.gymmembershipapp.database;

import com.example.gymmembershipapp.util.DbUtil;

import java.sql.*;

/**
 * @author Miroslav Kološnjaji
 */
public class Database {

    private static final Database INSTANCE = new Database();
    private Connection connection = null;

    private Database() {
    }

    public static Database getInstance(){
        return INSTANCE;
    }

    public void connectToDatabase() throws Exception{
        String url = DbUtil.getInstance().getUrl();
        String user = DbUtil.getInstance().getUrl();
        String password = DbUtil.getInstance().getPassword();

        try{
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        }catch (SQLException sqle){
            sqle.printStackTrace();
            throw new Exception("An error occured while connecting to the database\n" + sqle.getMessage());
        }
    }

    public void disconnectFromDatabase() throws Exception{
        try{
            if(connection != null && !connection.isClosed())
                connection.close();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            throw new Exception("An error occured while disconnecting from the database\n" + sqle.getMessage());
        }
    }

    public void confirmTransaction() throws Exception{
        try{
            connection.commit();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            throw new Exception("An error occured while confirming transaction\n" + sqle.getMessage());
        }
    }

    public void cancelTransaction() throws Exception{
        try{
            connection.rollback();
        }catch (SQLException sqle){
            sqle.printStackTrace();
            throw new Exception("An error occured while canceling transaction\n" + sqle.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
