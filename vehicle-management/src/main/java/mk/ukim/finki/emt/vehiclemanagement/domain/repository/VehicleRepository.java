package mk.ukim.finki.emt.vehiclemanagement.domain.repository;

import mk.ukim.finki.emt.vehiclemanagement.domain.models.Vehicle;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository <Vehicle, VehicleId>{
}
