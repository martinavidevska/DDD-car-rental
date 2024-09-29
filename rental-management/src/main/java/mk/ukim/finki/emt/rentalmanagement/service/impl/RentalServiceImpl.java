package mk.ukim.finki.emt.rentalmanagement.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.rentalmanagement.domain.exceptions.RentingNotFoundException;
import mk.ukim.finki.emt.rentalmanagement.domain.models.*;
import mk.ukim.finki.emt.rentalmanagement.domain.repository.LocationRepository;
import mk.ukim.finki.emt.rentalmanagement.domain.repository.RentalRepository;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.User;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.UserId;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.Vehicle;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.VehicleId;
import mk.ukim.finki.emt.rentalmanagement.service.RentalService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.LocationForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.rentalmanagement.xport.client.UserClient;
import mk.ukim.finki.emt.rentalmanagement.xport.client.VehicleClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Service;

import javax.validation.Validator;


import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class RentalServiceImpl implements RentalService {
    private final LocationRepository locationRepository;
    private final Validator validator;
    private final RentalRepository rentalRepository;
    private final VehicleClient vehicleClient;
    private final UserClient userClient;

    public RentalServiceImpl(LocationRepository locationRepository, Validator validator, RentalRepository rentalRepository, VehicleClient vehicleClient, UserClient userClient) {
        this.locationRepository = locationRepository;
        this.validator = validator;
        this.rentalRepository = rentalRepository;
        this.vehicleClient = vehicleClient;
        this.userClient = userClient;
    }


    @Override
    public Rental rent(RentForm rentForm) {
        Objects.requireNonNull(rentForm,"order must not be null.");
     return rentalRepository.saveAndFlush(toDomainObject(rentForm));

    }

    public Rental toDomainObject(RentForm rentForm) {
        VehicleId vehicleId = new VehicleId(rentForm.getVehicleId());
        UserId userId = new UserId(rentForm.getUserId());
        LocationId pickedFrom = new LocationId(rentForm.getPickedFrom());
        LocationId returnedTo = new LocationId(rentForm.getReturnedTo());
        return new Rental( rentForm.getStartRent(),rentForm.getEndRent(),
                vehicleId, pickedFrom, returnedTo, userId );
    }


    @Override
    public List<Rental> findAll() {
        return this.rentalRepository.findAll();
    }

    @Override
    public List<Rental> findAllByUserId(UserId userId) {
        return this.rentalRepository.findAllByUserId(userId);
    }

    @Override
    public Rental findById(RentalId id) {
        return rentalRepository.findById(id).orElseThrow(()->new RentingNotFoundException(id));
    }

    @Override
    public void pay(RentalId id, String paymentDetails) {
//        Rental rental=this.findById(id);
//        Money amount = rental.totalAmount();
//        Payment payment=new Payment(id,paymentDetails);
    }

//    @Override
//    public Money calculateAmount(Vehicle vehicle, LocalDate start, LocalDate end) {
//        long numberOfDays = ChronoUnit.DAYS.between(start, end);
//        return new Money(vehicle.getDailyPrice().getCurrency(),numberOfDays * vehicle
//                .getDailyPrice().getAmount());
//
//    }

    @Override
    public void deleteRental(RentalId id) {
        Rental rental=this.findById(id);
        this.rentalRepository.delete(rental);
    }

    @Override
    public LocationId addLocation(LocationForm locationForm) {
        Location location1= new Location(locationForm.getName(), locationForm.getCity(), locationForm.getState(), locationForm.getContact());
        locationRepository.save(location1);
        return location1.getId();
    }

    @Override
    public List<Location> findAllLocations() {
        return  locationRepository.findAll();
    }


    public Money totalAmount (Rental rental) {
        Vehicle vehicle = vehicleClient.getVehicle(rental.getVehicleId());
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found");
        }
        int numberOfDays = (int) ChronoUnit.DAYS.between(rental.getStartRent(), rental.getEndRent());
        return vehicle.getDailyPrice().multiply(numberOfDays);
    }

}
