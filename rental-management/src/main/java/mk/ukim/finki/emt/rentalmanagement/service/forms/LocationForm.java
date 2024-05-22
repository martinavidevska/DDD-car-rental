package mk.ukim.finki.emt.rentalmanagement.service.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LocationForm {

    @NotNull(message = "Location name is required")
    @Size(min = 2, max = 100, message = "Location name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "City is required")
    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    private String city;

    @NotNull(message = "State is required")
    @Size(min = 2, max = 100, message = "State must be between 2 and 100 characters")
    private String state;

    @NotNull(message = "Contact is required")
    @Size(min = 7, max = 15, message = "Contact must be between 7 and 15 characters")
    private String contact;
}
