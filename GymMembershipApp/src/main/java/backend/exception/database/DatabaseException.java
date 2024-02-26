package backend.exception.database;

/**
 * @author Miroslav Kološnjaji
 */
public class DatabaseException extends Exception{

    public DatabaseException(String errorMessage) {
        super(errorMessage);
    }
}
