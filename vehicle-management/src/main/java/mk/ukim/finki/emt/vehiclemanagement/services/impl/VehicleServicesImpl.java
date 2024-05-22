package mk.ukim.finki.emt.vehiclemanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.vehiclemanagement.domain.exceptions.VehicleNotFoundException;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.Vehicle;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleId;
import mk.ukim.finki.emt.vehiclemanagement.domain.repository.VehicleRepository;
import mk.ukim.finki.emt.vehiclemanagement.domain.valueobjects.LicensePlate;
import mk.ukim.finki.emt.vehiclemanagement.services.VehicleService;
import mk.ukim.finki.emt.vehiclemanagement.services.forms.VehicleForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleServicesImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    @Override
    public Vehicle findById(VehicleId id) {
        return this.vehicleRepository.findById(id).orElseThrow(()->new VehicleNotFoundException(id));
    }

    @Override
    public Vehicle addVehicle(VehicleForm vehicleForm) {
        Vehicle vehicle=new Vehicle(new LicensePlate(vehicleForm.getLicensePlate()), vehicleForm.getDailyPrice(),
                vehicleForm.getModel(),vehicleForm.getBrand(),vehicleForm.getSeats(),vehicleForm.getBags(),
               vehicleForm.getVehicleType(), vehicleForm.getPictureLink());
        vehicleRepository.save(vehicle);

        return vehicle;
    }

    @Override
    public List<Vehicle> getAll() {
        return this.vehicleRepository.findAll();
    }

    @Override
    public Vehicle rent(VehicleId vehicleId) {
        Vehicle vehicle=this.findById(vehicleId);
        vehicle.rent();
        return  vehicle;

    }

}
