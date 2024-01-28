package backend.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Miroslav Kološnjaji
 */
public class DbUtil {

    private static final DbUtil INSTANCE = new DbUtil();
    private Properties properties;

    private DbUtil(){
        properties = new Properties();
        try{
            properties.load(new FileInputStream("db.properties"));
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public static DbUtil getInstance(){
        return INSTANCE;
    }

    public String getUrl(){
        return properties.getProperty(getCurrentDb() + "_url");
    }

    public String getUser(){
        return properties.getProperty(getCurrentDb() + "_user");
    }

    public String getPassword(){
        return properties.getProperty(getCurrentDb() + "_password");
    }

    private String getCurrentDb(){
        return properties.getProperty("current_db");
    }


}
