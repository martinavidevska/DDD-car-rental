package mk.ukim.finki.emt.rentalmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.UserId;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

@Getter
public class User implements ValueObject {
    private final UserId userId;
    private final String username;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String driverLicenseNumber;

    public User() {
        this.userId = UserId.randomId(UserId.class);
        this.username = "";
        this.name = "";
        this.email = "";
        this.phoneNumber = "";
        this.driverLicenseNumber = "";
    }

    @JsonCreator
    public User(@JsonProperty("userId") UserId userId,
                @JsonProperty("username") String username,
                @JsonProperty("name") String name,
                @JsonProperty("email") String email,
                @JsonProperty("phoneNumber") String phoneNumber,
                @JsonProperty("driverLicenseNumber") String driverLicenseNumber) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.driverLicenseNumber = driverLicenseNumber;
    }
}
