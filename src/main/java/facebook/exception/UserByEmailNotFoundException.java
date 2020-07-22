package facebook.exception;

public class UserByEmailNotFoundException extends Exception {
    public UserByEmailNotFoundException(String message) {
        super(message);
    }
}
