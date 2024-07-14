package mk.ukim.finki.emt.rentalmanagement.api.rest;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Rental;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentalId;
import mk.ukim.finki.emt.rentalmanagement.service.RentalService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.rentalmanagement.xport.client.VehicleClient;
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

    @PostMapping()
    public Rental addRent(@RequestBody RentForm rentForm){
        return rentalService.rent(rentForm);
    }

    @GetMapping("/{id}")
    public Rental findRentalById (@PathVariable RentalId id){
        return rentalService.findById(id);
    }




}
