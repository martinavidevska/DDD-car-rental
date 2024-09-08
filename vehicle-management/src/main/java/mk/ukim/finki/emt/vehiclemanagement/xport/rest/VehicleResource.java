package mk.ukim.finki.emt.vehiclemanagement.xport.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.Vehicle;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleId;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleType;
import mk.ukim.finki.emt.vehiclemanagement.domain.repository.VehicleRepository;
import mk.ukim.finki.emt.vehiclemanagement.services.VehicleService;
import mk.ukim.finki.emt.vehiclemanagement.services.forms.VehicleForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
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
    @GetMapping("/types")
    public List<VehicleType> findAllTypes(){
        return List.of(VehicleType.values());
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Vehicle>> filterVehicles(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "dailyPrice", required = false) Double dailyPrice) {

        try {
            List<Vehicle> vehicles = vehicleService.filter(type, dailyPrice, model);
            return ResponseEntity.ok(vehicles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
