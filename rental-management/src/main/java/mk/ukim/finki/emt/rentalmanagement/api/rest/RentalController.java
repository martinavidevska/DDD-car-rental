package mk.ukim.finki.emt.rentalmanagement.api.rest;

import mk.ukim.finki.emt.rentalmanagement.domain.models.LocationId;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Rental;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentalId;
import mk.ukim.finki.emt.rentalmanagement.service.RentalService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.LocationForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.rentalmanagement.xport.client.VehicleClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rental")
public class RentalController {

    public final RentalService rentalService;
    public final VehicleClient vehicleClient;

    public RentalController(RentalService rentalService, VehicleClient vehicleClient) {
        this.rentalService = rentalService;
        this.vehicleClient = vehicleClient;
    }

    @GetMapping()
    public List<Rental> getAllRentals (){
        return rentalService.findAll();
    }

    @PostMapping("/rent")
    public ResponseEntity<Rental> rentVehicle(@RequestBody RentForm form) {
        Rental rental = rentalService.rent(form);
        return ResponseEntity.ok(rental);
    }
    @GetMapping("/{id}")
    public Rental findRentalById (@PathVariable RentalId id){
        return rentalService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable RentalId id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/location")
    public ResponseEntity<LocationId> addLocation(@RequestBody LocationForm locationForm) {
        LocationId locationId = rentalService.addLocation(locationForm);
        return ResponseEntity.ok(locationId);
    }

}
