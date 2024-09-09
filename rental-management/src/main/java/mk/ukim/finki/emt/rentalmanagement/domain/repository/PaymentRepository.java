package mk.ukim.finki.emt.rentalmanagement.domain.repository;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Payment;
import mk.ukim.finki.emt.rentalmanagement.domain.models.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {
}
