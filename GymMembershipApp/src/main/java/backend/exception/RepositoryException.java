package backend.exception;

/**
 * @author Miroslav Kološnjaji
 */
public class RepositoryException extends Exception{

    public RepositoryException(String errorMessage) {
        super(errorMessage);
    }
}
