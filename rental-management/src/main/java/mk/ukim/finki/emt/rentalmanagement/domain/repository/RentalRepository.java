package mk.ukim.finki.emt.rentalmanagement.domain.repository;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Rental;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, RentalId> {
}
