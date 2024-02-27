package server.exception.service;

/**
 * @author Miroslav Kološnjaji
 */
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
