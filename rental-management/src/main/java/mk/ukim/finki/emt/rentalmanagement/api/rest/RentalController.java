package mk.ukim.finki.emt.rentalmanagement.api.rest;

import mk.ukim.finki.emt.rentalmanagement.domain.models.*;
import mk.ukim.finki.emt.rentalmanagement.domain.repository.LocationRepository;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.UserId;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.Vehicle;
import mk.ukim.finki.emt.rentalmanagement.service.PaymentService;
import mk.ukim.finki.emt.rentalmanagement.service.RentalService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.LocationForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.PaymentForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.rentalmanagement.service.impl.LocationService;
import mk.ukim.finki.emt.rentalmanagement.xport.client.VehicleClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rental")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class RentalController {

    public final RentalService rentalService;
    public final VehicleClient vehicleClient;
    public final PaymentService  paymentService;
    private final LocationService locationService;

    public RentalController(RentalService rentalService, VehicleClient vehicleClient, PaymentService paymentService, LocationService locationService) {
        this.rentalService = rentalService;
        this.vehicleClient = vehicleClient;
        this.paymentService = paymentService;
        this.locationService = locationService;
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
    @PostMapping("/add-location")
    public ResponseEntity<LocationId> addLocation(@RequestBody LocationForm locationForm) {
        LocationId locationId = rentalService.addLocation(locationForm);
        return ResponseEntity.ok(locationId);
    }
    @GetMapping("/locations/{locationId}")
    public ResponseEntity<Location> findLocationById(@PathVariable String locationId) {
        LocationId id = new LocationId(locationId);
        return ResponseEntity.ok(locationService.findById(id));

    }
    @GetMapping("/locations")
    public List<Location> findAllLocations() {
        return rentalService.findAllLocations();
    }
    @GetMapping("/list-vehicles")
    public List<Vehicle> listVehicles() {
        return vehicleClient.findAll();
    }
    @PostMapping("/payment")
    private ResponseEntity<Payment> addPayment(@RequestBody PaymentForm paymentForm) {
        Payment payment = this.paymentService.save(paymentForm);
        return ResponseEntity.ok(payment);
    }
    @GetMapping("/by-user/{userId}")
    private ResponseEntity<List<Rental>> getRentalsByUser(@PathVariable String userId) {
        UserId userIdentifier = new UserId(userId);
        return  ResponseEntity.ok(this.rentalService.findAllByUserId(userIdentifier));
    }

}
