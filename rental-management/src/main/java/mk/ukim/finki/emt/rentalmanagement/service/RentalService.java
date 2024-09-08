package mk.ukim.finki.emt.rentalmanagement.service;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Location;
import mk.ukim.finki.emt.rentalmanagement.domain.models.LocationId;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Rental;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentalId;
import mk.ukim.finki.emt.rentalmanagement.domain.repository.LocationRepository;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.Vehicle;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.VehicleId;
import mk.ukim.finki.emt.rentalmanagement.service.forms.LocationForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalService {
    Rental rent(RentForm rentForm);
    List<Rental> findAll();
    List<Rental> findAllByUsername(String username);
    Rental findById(RentalId id);
    void pay(RentalId id, String paymentDetails);
   // Money calculateAmount(Vehicle vehicle, LocalDate start, LocalDate end);
    void deleteRental(RentalId id);
    LocationId addLocation(LocationForm locationForm);
    List<Location> findAllLocations();
    Money totalAmount(Rental rental, Vehicle vehicle);



}
