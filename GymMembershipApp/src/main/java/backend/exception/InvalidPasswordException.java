package backend.exception;

/**
 * @author Miroslav Kološnjaji
 */
public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
