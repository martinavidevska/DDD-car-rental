package mk.ukim.finki.emt.vehiclemanagement.domain.repository;

import mk.ukim.finki.emt.vehiclemanagement.domain.models.Vehicle;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleId;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository <Vehicle, VehicleId>{
    Vehicle findByLicensePlate(String licensePlate);
    List<Vehicle> findAllByVehicleType(VehicleType vehicleType);
    Vehicle findByModel(String model);
    Vehicle findBySeats(Integer seats);
    List<Vehicle>findAllByModel(String model);
    List<Vehicle> findAllByDailyPriceAmountIsLessThanEqual(Double dailyPrice);
    List<Vehicle> findAllByModelAndVehicleType(String model, VehicleType vehicleTypeId);
    List<Vehicle>findAllByVehicleTypeAndDailyPriceAmountIsLessThanEqual(VehicleType vehicleTypeId, Double dailyPrice);
    List<Vehicle>findAllByVehicleTypeAndDailyPriceAmountIsLessThanEqualAndModel(VehicleType vehicleTypeId,Double dailyPrice, String model);
    List<Vehicle> findAllByModelAndDailyPriceAmountIsLessThanEqual(String model,Double dailyPrice);

}

