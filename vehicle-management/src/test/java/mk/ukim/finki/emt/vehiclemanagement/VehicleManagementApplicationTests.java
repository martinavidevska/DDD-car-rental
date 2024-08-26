package mk.ukim.finki.emt.vehiclemanagement;

import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.Vehicle;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleType;
import mk.ukim.finki.emt.vehiclemanagement.domain.valueobjects.LicensePlate;
import mk.ukim.finki.emt.vehiclemanagement.services.VehicleService;
import mk.ukim.finki.emt.vehiclemanagement.services.forms.VehicleForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class VehicleManagementApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private VehicleService vehicleService;

    List<Vehicle> initialVehicleList = new ArrayList<>();

    @Test
    public void testAddAndFetchVehicles() {
            VehicleForm v1Form = new VehicleForm();
            v1Form.setLicensePlate("123ABC");
            v1Form.setDailyPrice(new Money(Currency.valueOf("MKD"), 1500));
            v1Form.setModel("Model S");
            v1Form.setBrand("Tesla");
            v1Form.setSeats(5);
            v1Form.setBags(2);
            v1Form.setVehicleType(VehicleType.SEDAN);
            v1Form.setPictureLink("http://example.com/tesla-model-s.jpg");

            VehicleForm v2Form = new VehicleForm();
            v2Form.setLicensePlate("456DEF");
            v2Form.setDailyPrice(new Money(Currency.valueOf("MKD"), 2000));
            v2Form.setModel("X5");
            v2Form.setBrand("BMW");
            v2Form.setSeats(5);
            v2Form.setBags(3);
            v2Form.setVehicleType(VehicleType.SUV);
            v2Form.setPictureLink("http://example.com/bmw-x5.jpg");

            // Add the vehicles using the service
            vehicleService.addVehicle(v1Form);
            vehicleService.addVehicle(v2Form);

            // Re-fetch the vehicle list
            initialVehicleList = vehicleService.getAll();


        // Ensure vehicles were added
        Assertions.assertNotNull(initialVehicleList);
        Assertions.assertTrue(initialVehicleList.size() >= 2);

        // Validate that the vehicles have the expected attributes
        Vehicle v1 = initialVehicleList.get(0);
        Vehicle v2 = initialVehicleList.get(1);

        Assertions.assertEquals("123ABC", v1.getLicensePlate().getLicensePlate());
        Assertions.assertEquals("Tesla", v1.getBrand());
        Assertions.assertEquals("Model S", v1.getModel());
        Assertions.assertEquals(1500, v1.getDailyPrice().getAmount());

        Assertions.assertEquals("456DEF", v2.getLicensePlate().getLicensePlate());
        Assertions.assertEquals("BMW", v2.getBrand());
        Assertions.assertEquals("X5", v2.getModel());
        Assertions.assertEquals(2000, v2.getDailyPrice().getAmount());
    }
}
