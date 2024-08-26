package mk.ukim.finki.emt.rentalmanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.rentalmanagement.domain.models.LocationId;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.User;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.UserId;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.Vehicle;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.VehicleId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class RentForm {

    @NotNull(message = "Start date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startRent;

    @NotNull(message = "End date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endRent;

    @NotNull(message = "Vehicle ID is required")
    private VehicleId vehicleId;

    @NotNull(message = "User ID is required")
    private UserId userId;

    @NotNull(message = "Picked from location is required")
    private LocationId pickedFrom;

    @NotNull(message = "Returned to location is required")
    private LocationId returnedTo;

}
