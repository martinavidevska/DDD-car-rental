package mk.ukim.finki.emt.vehiclemanagement.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

public enum VehicleType{
    SportCar, SUV, SEDAN, MINIVAN,  CONVERTIBLE, PickUpTruck,
}