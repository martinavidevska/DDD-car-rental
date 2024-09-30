package mk.ukim.finki.emt.rentalmanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentalId;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.VehicleId;

import javax.validation.constraints.NotNull;

@Data
public class  PaymentForm {

    @NotNull(message = "Rental ID is required")
    private RentalId rentalId;

    @NotNull(message = "")
    private String paymentMethod;

    public PaymentForm(RentalId rentalId, String paymentMethod) {
        this.rentalId = rentalId;
        this.paymentMethod = paymentMethod;
    }
}
