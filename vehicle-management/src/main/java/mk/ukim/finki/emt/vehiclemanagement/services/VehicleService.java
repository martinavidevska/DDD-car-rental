package mk.ukim.finki.emt.vehiclemanagement.services;

import mk.ukim.finki.emt.vehiclemanagement.domain.models.Vehicle;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleId;
import mk.ukim.finki.emt.vehiclemanagement.services.forms.VehicleForm;

import java.util.List;

public interface VehicleService {
    Vehicle findById(VehicleId id);
    Vehicle addVehicle(VehicleForm vehicleForm);
    List<Vehicle> getAll();
    Vehicle rent (VehicleId vehicleId);

}
