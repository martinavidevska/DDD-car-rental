package mk.ukim.finki.emt.rentalmanagement.service;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Payment;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    Payment save(Payment payment);
}
