package mk.ukim.finki.emt.vehiclemanagement.xport.events;


import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.rents.VehicleAdd;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleType;
import mk.ukim.finki.emt.vehiclemanagement.services.VehicleService;
import mk.ukim.finki.emt.vehiclemanagement.services.forms.VehicleForm;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleEventListener {

    private final VehicleService vehicleService;


    @KafkaListener(topics = TopicHolder.TOPIC_VEHICLE_ADD, groupId = "vehicleManagement")
    public void consumeVehicleAddEvent(String jsonMessage) {
        try {

            VehicleAdd event = DomainEvent.fromJson(jsonMessage, VehicleAdd.class);

            VehicleForm vehicleForm = new VehicleForm();
            vehicleForm.setLicensePlate(event.getLicensePlate());
            vehicleForm.setVehicleType(VehicleType.valueOf(event.getVehicleType()));
            vehicleForm.setBags(event.getBags());
            vehicleForm.setModel(event.getModel());
            vehicleForm.setSeats(event.getSeats());
            vehicleForm.setDailyPrice(event.getDailyPrice());
            vehicleForm.setLicensePlate(event.getPictureLink());
            vehicleForm.setBrand(event.getBrand());
            vehicleService.addVehicle(vehicleForm);
        }catch (Exception e){

        }
    }
}
