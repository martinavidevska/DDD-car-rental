package mk.ukim.finki.emt.rentalmanagement.domain.repository;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Location;
import mk.ukim.finki.emt.rentalmanagement.domain.models.LocationId;
import mk.ukim.finki.emt.rentalmanagement.service.forms.LocationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, LocationId> {
}
