package mk.ukim.finki.emt.usermanagement.domain.exceptions;

import mk.ukim.finki.emt.usermanagement.domain.models.UserId;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String  username ) {
        super(String.format("The renting with id %s is not found",username));
    }
}
