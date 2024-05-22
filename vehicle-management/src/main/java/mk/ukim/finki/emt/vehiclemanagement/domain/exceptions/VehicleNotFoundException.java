package mk.ukim.finki.emt.vehiclemanagement.domain.exceptions;


import mk.ukim.finki.emt.vehiclemanagement.domain.models.Vehicle;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleId;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(VehicleId id ) {
        super(String.format("The vehicle with license plate %s is not found",id));
    }

}
