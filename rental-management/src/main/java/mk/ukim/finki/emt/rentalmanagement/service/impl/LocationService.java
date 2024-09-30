package mk.ukim.finki.emt.rentalmanagement.service.impl;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Location;
import mk.ukim.finki.emt.rentalmanagement.domain.models.LocationId;
import mk.ukim.finki.emt.rentalmanagement.domain.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    private final LocationRepository locationRepository;

    public Location findById(LocationId id) {
        return locationRepository.findById(id).orElse(null);
    }
}
