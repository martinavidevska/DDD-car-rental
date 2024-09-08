package mk.ukim.finki.emt.vehiclemanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.vehiclemanagement.domain.exceptions.VehicleNotFoundException;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.Vehicle;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleId;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleType;
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
        Money dailyPrice = new Money( Currency.valueOf(vehicleForm.getCurrency()), vehicleForm.getAmount());
        Vehicle vehicle=new Vehicle(vehicleForm.getLicensePlate(), dailyPrice,
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

    @Override
    public List<Vehicle> filter(String vehicleType, Double dailyPrice, String model) {
        if(vehicleType!=null && dailyPrice!=null && model!=null) {
            VehicleType type=VehicleType.valueOf(vehicleType);
            return this.vehicleRepository.findAllByVehicleTypeAndDailyPriceAmountIsLessThanEqualAndModel(type,dailyPrice,model);
        }
        else if(vehicleType!=null && dailyPrice!=null){
            VehicleType type=VehicleType.valueOf(vehicleType);
            return this.vehicleRepository.findAllByVehicleTypeAndDailyPriceAmountIsLessThanEqual(type,dailyPrice);
        }
        else if (vehicleType != null && model != null) {
            VehicleType type=VehicleType.valueOf(vehicleType);
            return this.vehicleRepository.findAllByModelAndVehicleType(model,type);
        }
        else if(model!=null && dailyPrice!=null){
            return this.vehicleRepository.findAllByModelAndDailyPriceAmountIsLessThanEqual(model,dailyPrice);
        }
        else if(model!=null){
            return this.vehicleRepository.findAllByModel(model);
        }
        else if(dailyPrice!=null){
            return this.vehicleRepository.findAllByDailyPriceAmountIsLessThanEqual(dailyPrice);
        }
        else if(vehicleType!=null){
            VehicleType type=VehicleType.valueOf(vehicleType);
            return this.vehicleRepository.findAllByVehicleType(type);
        }
        else return this.getAll();
    }

}
