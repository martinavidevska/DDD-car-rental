package mk.ukim.finki.emt.rentalmanagement.service;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Location;
import mk.ukim.finki.emt.rentalmanagement.domain.models.LocationId;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Rental;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentalId;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.UserId;
import mk.ukim.finki.emt.rentalmanagement.service.forms.LocationForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import java.util.List;

public interface RentalService {
    Rental rent(RentForm rentForm);
    List<Rental> findAll();
    List<Rental> findAllByUserId(UserId userId);
    Rental findById(RentalId id);
    void pay(RentalId id, String paymentDetails);
   // Money calculateAmount(Vehicle vehicle, LocalDate start, LocalDate end);
    void deleteRental(RentalId id);
    LocationId addLocation(LocationForm locationForm);
    List<Location> findAllLocations();
    Money totalAmount(Rental rental);



}
