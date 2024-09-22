package mk.ukim.finki.emt.usermanagement.service.forms.dto.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String username;
    private String driverLicenceNumber;
    private String email;
    private String phoneNumber;
    private String address;
}
