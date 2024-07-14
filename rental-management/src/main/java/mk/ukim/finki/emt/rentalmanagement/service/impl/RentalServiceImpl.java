package mk.ukim.finki.emt.rentalmanagement.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emt.rentalmanagement.domain.exceptions.RentingNotFoundException;
import mk.ukim.finki.emt.rentalmanagement.domain.models.*;
import mk.ukim.finki.emt.rentalmanagement.domain.repository.LocationRepository;
import mk.ukim.finki.emt.rentalmanagement.domain.repository.RentalRepository;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.Vehicle;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.VehicleId;
import mk.ukim.finki.emt.rentalmanagement.service.RentalService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.LocationForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.rentalmanagement.xport.client.VehicleClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Service;

import javax.validation.Validator;



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

    public RentalServiceImpl(LocationRepository locationRepository, Validator validator, RentalRepository rentalRepository, VehicleClient vehicleClient) {
        this.locationRepository = locationRepository;
        this.validator = validator;
        this.rentalRepository = rentalRepository;
        this.vehicleClient = vehicleClient;
    }


    @Override
    public Rental rent(RentForm rentForm) {
        Objects.requireNonNull(rentForm,"order must not be null.");
        var constraintViolations = validator.validate(rentForm);
//        if (constraintViolations.size()>0) {
//            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
//        }
     return rentalRepository.saveAndFlush(toDomainObject(rentForm));

    }

    public Rental toDomainObject(RentForm rentForm) {
        VehicleId vehicleId = rentForm.getVehicleId();


//       Money totalAmount= this.calculateAmount(vehicleId, rentForm.getStartRent(),rentForm.getEndRent());
        return new Rental( rentForm.getStartRent(),rentForm.getEndRent(),
                vehicleId, rentForm.getPickedFrom(),rentForm.getReturnedTo() );

    }


    @Override
    public List<Rental> findAll() {
        return this.rentalRepository.findAll();
    }

//    @Override
//    public List<Rental> findAllByUsername(String username) {
//        return this.rentalRepository.;
//    }

    @Override
    public Rental findById(RentalId id) {
        return rentalRepository.findById(id).orElseThrow(()->new RentingNotFoundException(id));
    }

    @Override
    public void pay(RentalId id, String paymentDetails) {
        Rental rental=this.findById(id);
        Payment payment=new Payment(rental,paymentDetails);
        rental.pay(payment);
        rentalRepository.saveAndFlush(rental);


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
    public Money totalAmount(Rental rental, Vehicle vehicle) {

        //TODO
       // Vehicle vehicle = vehicleClient.getVehicle(rental.getVehicleId());
//        if (vehicle == null) {
//            throw new IllegalArgumentException("Vehicle not found");
//        }
//        int numberOfDays = (int) ChronoUnit.DAYS.between(rental.getStartRent(), rental.getEndRent());
//        return vehicle.getDailyPrice().multiply(numberOfDays);
        return null;
    }
}
