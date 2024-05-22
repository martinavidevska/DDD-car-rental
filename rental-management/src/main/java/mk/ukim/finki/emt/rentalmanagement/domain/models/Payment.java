package mk.ukim.finki.emt.rentalmanagement.domain.models;

import jakarta.persistence.*;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.VehicleId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity
@Table(name="payment")
public class Payment extends AbstractEntity<PaymentId> {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfPayment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental rental;

    private String paymentDetails;

    public Payment(Rental rental, String paymentDetails) {
        this.dateOfPayment=LocalDate.now();
        this.rental = rental;
        this.paymentDetails = paymentDetails;
    }
}
