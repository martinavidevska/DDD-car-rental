package mk.ukim.finki.emt.vehiclemanagement.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.vehiclemanagement.domain.valueobjects.LicensePlate;

@Entity
@Table(name="vehicle")
@Getter
public class Vehicle extends AbstractEntity<VehicleId> {
    private String licensePlate;


    @AttributeOverrides({
            @AttributeOverride(name = "amount", column =
            @Column(name = "daily_price_amount")),
            @AttributeOverride(name = "currency", column =
            @Column(name = "daily_price_currency"))
    })
    private Money dailyPrice;
    private String model;
    private String brand;
    private Integer seats;
    private Integer bags;
    private boolean available;
    @Enumerated(EnumType.STRING)
    @Column(name="vehicle_type")
    private VehicleType vehicleType;
    @JoinColumn(name="picture_link")
    private String pictureLink;

    private Vehicle(){
        super(VehicleId.randomId(VehicleId.class));
    }

    public Vehicle(String licensePlate, Money dailyPrice, String model, String brand, Integer seats, Integer bags, VehicleType vehicleType, String pictureLink) {
        super(VehicleId.randomId(VehicleId.class));
        this.licensePlate = licensePlate;
        this.dailyPrice = dailyPrice;
        this.model = model;
        this.brand = brand;
        this.seats = seats;
        this.bags = bags;
       this.available=true;
        this.vehicleType = vehicleType;
        this.pictureLink = pictureLink;
    }

    public void rent(){
        this.available=false;
   }


}
