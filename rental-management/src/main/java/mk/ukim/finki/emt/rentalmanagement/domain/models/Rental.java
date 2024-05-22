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


    private LocationId pickedFrom;

    private LocationId returnedTo;

    @OneToOne(mappedBy = "rental", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    @Transient
    private VehicleClient vehicleClient;
    public void pay(Payment payment){
        this.payment=payment;
    }

    public Rental(LocalDate startRent, LocalDate endRent, VehicleId vehicle, LocationId pickedFrom, LocationId returnedTo) {
        super(RentalId.randomId(RentalId.class));
        this.startRent = startRent;
        this.endRent = endRent;
        this.vehicleId = vehicle;
        this.pickedFrom = pickedFrom;
        this.returnedTo = returnedTo;

    }

    public Rental() {

    }

//    public Money totalAmount(){
//        Vehicle vehicle = vehicleClient.getVehicle(this.vehicleId);
//        if (vehicle == null) {
//            throw new IllegalArgumentException("Vehicle not found");
//        }
//        int numberOfDays = (int) ChronoUnit.DAYS.between(startRent, endRent);
//        return vehicle.getDailyPrice().multiply(numberOfDays);
//    }



}
