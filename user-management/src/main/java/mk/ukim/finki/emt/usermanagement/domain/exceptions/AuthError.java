package mk.ukim.finki.emt.usermanagement.domain.exceptions;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



public class AuthError extends RuntimeException {

    public ResponseEntity<String> AuthError (){
            return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
    }

}
