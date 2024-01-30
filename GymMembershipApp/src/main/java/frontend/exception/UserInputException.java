package frontend.exception;

/**
 * @author Miroslav Kološnjaji
 */
public class UserInputException extends Exception{
    public UserInputException(String errorMessage) {
        super(errorMessage);
    }
}
