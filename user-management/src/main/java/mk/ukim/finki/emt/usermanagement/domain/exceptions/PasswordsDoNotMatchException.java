package mk.ukim.finki.emt.usermanagement.domain.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException() {
        super("The passwords do not match");
    }
}
