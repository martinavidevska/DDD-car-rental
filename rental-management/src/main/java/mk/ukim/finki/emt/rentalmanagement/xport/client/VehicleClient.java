package mk.ukim.finki.emt.rentalmanagement.xport.client;


import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.Vehicle;
import mk.ukim.finki.emt.rentalmanagement.domain.valueobjects.VehicleId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class VehicleClient {
    private  final RestTemplate restTemplate;
    private final String serverUrl;

    public VehicleClient(@Value("${app.vehicle-management.url}") String serverUrl){
        this.serverUrl=serverUrl;
        this.restTemplate=new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri(){
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }
    public List<Vehicle> findAll(){
        try{
            return restTemplate.exchange(uri().path("/api/vehicle").build().toUri(), HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Vehicle>>(){

                    }).getBody();
        }
        catch (Exception e){
            return Collections.emptyList();
        }
    }
    public Vehicle getVehicle(VehicleId vehicleId){
        try {
            return restTemplate.exchange(uri().path("/api/vehicle/{id}").buildAndExpand(vehicleId.getId()).toUri(),
                    HttpMethod.GET, null, new ParameterizedTypeReference<Vehicle>() {}).getBody();
        } catch (Exception e) {
            return null;
        }
    }
    public Vehicle addVehicle(Vehicle vehicle) {
        try {
            ResponseEntity<Vehicle> response = restTemplate.exchange(
                    uri().path("/api/vehicle/add").build().toUri(),
                    HttpMethod.POST,
                    new HttpEntity<>(vehicle),
                    Vehicle.class);
            return response.getBody();
        } catch (Exception e) {
            // Handle exception appropriately, e.g., logging
            return null;
        }
    }
}
