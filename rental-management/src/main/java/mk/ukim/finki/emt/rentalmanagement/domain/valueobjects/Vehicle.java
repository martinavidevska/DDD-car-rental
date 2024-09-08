package mk.ukim.finki.emt.rentalmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Vehicle implements ValueObject {
    private final VehicleId vehicleId;
    private final String licensePlate;
    private final Money dailyPrice;
    private final String model;
    private final String brand;
    private final int seats;
    private final int bags;
    private final boolean available;
    private final String vehicleType;
    private final String pictureLink;

    @JsonCreator
    public Vehicle(@JsonProperty("id") VehicleId vehicleId,
                   @JsonProperty("licensePlate") String licensePlate,
                   @JsonProperty("dailyPrice") Money dailyPrice,
                   @JsonProperty("model") String model,
                   @JsonProperty("brand") String brand,
                   @JsonProperty("seats") int seats,
                   @JsonProperty("bags") int bags,
                   @JsonProperty("available") boolean available,
                   @JsonProperty("vehicleType") String vehicleType,
                   @JsonProperty("pictureLink") String pictureLink) {
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.dailyPrice = dailyPrice;
        this.model = model;
        this.brand = brand;
        this.seats = seats;
        this.bags = bags;
        this.available = available;
        this.vehicleType = vehicleType;
        this.pictureLink = pictureLink;
    }
}
