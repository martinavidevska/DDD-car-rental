package mk.ukim.finki.emt.rentalmanagement.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.UserId;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.Vehicle;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.VehicleId;
import mk.ukim.finki.emt.rentalmanagement.xport.client.VehicleClient;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;


@Entity
@Getter
@Table(name="rental")
public class Rental extends AbstractEntity<RentalId> {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_rent", nullable = false)
    private LocalDate startRent;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_rent")
    private LocalDate endRent;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column =
            @Column(name = "daily_price_amount")),
            @AttributeOverride(name = "currency", column =
            @Column(name = "daily_price_currency"))
    })


    @AttributeOverride(name="id", column=@Column(name="vehicle_id", nullable=false, length=255))
    private VehicleId vehicleId;

    @AttributeOverride(name="id", column=@Column(name="user_id", nullable=false, length=255))
    private UserId userId;


    private LocationId pickedFrom;

    private LocationId returnedTo;


    public Rental(LocalDate startRent, LocalDate endRent, VehicleId vehicle, LocationId pickedFrom, LocationId returnedTo,UserId userId) {
        super(RentalId.randomId(RentalId.class));
        this.startRent = startRent;
        this.endRent = endRent;
        this.vehicleId = vehicle;
        this.pickedFrom = pickedFrom;
        this.userId = userId;
        this.returnedTo = returnedTo;

    }

    public Rental() {

    }


}
