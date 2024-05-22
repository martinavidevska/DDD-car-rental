package mk.ukim.finki.emt.sharedkernel.domain.events.rents;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;


@Getter
public class VehicleAdd extends DomainEvent {
    private  String licensePlate;
    private  Money dailyPrice;
    private  String model;
    private  String brand;
    private  Integer seats;
    private  Integer bags;

    private  String vehicleType;
    private  String pictureLink;

    public VehicleAdd(String topic) {
        super(TopicHolder.TOPIC_VEHICLE_ADD);
    }


    public VehicleAdd( String licensePlate, Money dailyPrice, String model, String brand, Integer seats, Integer bags, String vehicleType, String pictureLink) {
        super(TopicHolder.TOPIC_VEHICLE_ADD);
        this.licensePlate = licensePlate;
        this.dailyPrice = dailyPrice;
        this.model = model;
        this.brand = brand;
        this.seats = seats;
        this.bags = bags;
        this.vehicleType = vehicleType;
        this.pictureLink = pictureLink;
    }


}
