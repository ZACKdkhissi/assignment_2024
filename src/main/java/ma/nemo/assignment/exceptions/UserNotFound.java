package ma.nemo.assignment.exceptions;

public class UserNotFound extends Exception {

    private static final long serialVersionUID = 1L;

    public UserNotFound() {
    }

    public UserNotFound(String message) {
        super(message);
    }
}
