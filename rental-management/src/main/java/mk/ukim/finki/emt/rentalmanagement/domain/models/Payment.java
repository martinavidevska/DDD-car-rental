package mk.ukim.finki.emt.rentalmanagement.domain.models;

import jakarta.persistence.*;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.VehicleId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Map;


@Entity
@Table(name="payment")
public class Payment extends AbstractEntity<PaymentId> {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfPayment;


    private RentalId rentalId;

    private String paymentDetails;

    public Payment(RentalId rentalId, String paymentDetails) {
        this.dateOfPayment=LocalDate.now();
        this.rentalId = rentalId;
        this.paymentDetails = paymentDetails;
    }

    public Payment() {

    }
}
