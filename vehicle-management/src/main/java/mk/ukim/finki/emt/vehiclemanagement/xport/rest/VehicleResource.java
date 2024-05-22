package mk.ukim.finki.emt.vehiclemanagement.xport.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.Vehicle;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleId;
import mk.ukim.finki.emt.vehiclemanagement.domain.repository.VehicleRepository;
import mk.ukim.finki.emt.vehiclemanagement.services.VehicleService;
import mk.ukim.finki.emt.vehiclemanagement.services.forms.VehicleForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@AllArgsConstructor
public class VehicleResource {
    private final VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> listAll(){
        return vehicleService.getAll();
    }

    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable VehicleId id){
        return vehicleService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Vehicle> add(@Valid @RequestBody VehicleForm vehicleForm){
        return ResponseEntity.ok(vehicleService.addVehicle(vehicleForm));
    }
}
