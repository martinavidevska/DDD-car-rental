package mk.ukim.finki.emt.rentalmanagement.service;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Location;
import mk.ukim.finki.emt.rentalmanagement.domain.models.LocationId;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Rental;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentalId;
import mk.ukim.finki.emt.rentalmanagement.domain.repository.LocationRepository;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.User;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.Vehicle;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.VehicleId;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.VehicleType;
import mk.ukim.finki.emt.rentalmanagement.service.RentalService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.LocationForm;
import mk.ukim.finki.emt.rentalmanagement.service.forms.RentForm;
import mk.ukim.finki.emt.rentalmanagement.xport.client.UserClient;
import mk.ukim.finki.emt.rentalmanagement.xport.client.VehicleClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class RentalServiceImplTest {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private VehicleClient vehicleClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private LocationRepository locationRepository;

    public Vehicle newVehicle(Money dailyPrice) {
        return new Vehicle(VehicleId.randomId(VehicleId.class), dailyPrice,true);
    }

    @Test
    public void testUserClient(){
        List<User> users = userClient.findAll();
        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    public void testPlaceRental() {
        Vehicle vehicle1 = this.newVehicle(Money.valueOf(Currency.MKD, 1500));
        Vehicle vehicle2 = newVehicle( Money.valueOf(Currency.MKD, 1000));

        LocationForm locationForm= new LocationForm();
        locationForm.setCity("skopje");
        locationForm.setName("Skopje-centar");
        locationForm.setState("Macedonia");
        locationForm.setContact("071345678");

        LocationId locationId=this.rentalService.addLocation(locationForm);

        RentForm rentalForm = new RentForm();
        rentalForm.setStartRent(LocalDate.now());
        rentalForm.setEndRent(LocalDate.now().plusDays(2));
        rentalForm.setVehicleId(vehicle1.getVehicleId());
        rentalForm.setPickedFrom(locationId);
        rentalForm.setReturnedTo(locationId);

        RentalId newRentalId = rentalService.rent(rentalForm).getId();
        Rental newRental = rentalService.findById(newRentalId);
        System.out.println(rentalService.totalAmount(newRental,vehicle1).getAmount());
        Assertions.assertEquals(rentalService.totalAmount(newRental,vehicle1), Money.valueOf(Currency.MKD, 3000));
    }

    @Test
    public void testPlaceRentalWithRealData() {


        List<Vehicle> vehicleList = vehicleClient.findAll();
        Vehicle v3 = vehicleList.get(0);
        Vehicle v2 = vehicleList.get(1);
        VehicleId id= v3.getVehicleId();

        Vehicle v1 = vehicleClient.getVehicle(id);
        List<User>users = userClient.findAll();
        System.out.println(users.size());
        User user1 = users.get(0);


        double p = v1.getDailyPrice().getAmount();
        double p1 = v2.getDailyPrice().getAmount();

        List<Location> locationList = locationRepository.findAll();
        Location location= locationList.get(0);
        Location location1 = locationList.get(1);

        RentForm rentalForm1 = new RentForm();
        rentalForm1.setStartRent(LocalDate.now());
        rentalForm1.setEndRent(LocalDate.now().plusDays(1));
        rentalForm1.setVehicleId(v1.getVehicleId());
        rentalForm1.setReturnedTo(location.getId());
        rentalForm1.setPickedFrom(location1.getId());
        rentalForm1.setUserId(user1.getUserId());

        RentalId newRentalId1 = rentalService.rent(rentalForm1).getId();
        Rental newRental1 = rentalService.findById(newRentalId1);

        RentForm rentalForm2 = new RentForm();
        rentalForm2.setStartRent(LocalDate.now());
        rentalForm2.setEndRent(LocalDate.now().plusDays(2));
        rentalForm2.setVehicleId(v2.getVehicleId());
        rentalForm2.setReturnedTo(location.getId());
        rentalForm2.setPickedFrom(location1.getId());
        rentalForm2.setUserId(user1.getUserId());

        RentalId newRentalId2 = rentalService.rent(rentalForm2).getId();
        Rental newRental2 = rentalService.findById(newRentalId2);

        Money totalAmount1 = v1.getDailyPrice().multiply(1);
        double totalAmount2 = v2.getDailyPrice().multiply(2).getAmount();

        List<Rental> rentalsByUser= this.rentalService.findAllByUsername("johndoe");

       // Assertions.assertEquals(rentalService.totalAmount(newRental1, v1).getAmount(), totalAmount1.getAmount());
        double what= rentalService.totalAmount(newRental1,v1).getAmount();
        Assertions.assertEquals(rentalService.totalAmount(newRental1,v1), totalAmount1);
//        Assertions.assertEquals(newRental1, rentalsByUser.getFirst());
    }

}
