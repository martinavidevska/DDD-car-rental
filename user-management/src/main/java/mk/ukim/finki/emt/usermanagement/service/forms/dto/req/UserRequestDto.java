package mk.ukim.finki.emt.usermanagement.service.forms.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

        private String name;
        private String username;
        private String password;
        private String repeatPassword;
        private String driverLicenceNumber;
        private String phoneNumber;
        private String address;
        private String email;


}
