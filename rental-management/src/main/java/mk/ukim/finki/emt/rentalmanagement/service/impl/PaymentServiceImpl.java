package mk.ukim.finki.emt.rentalmanagement.service.impl;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Payment;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Rental;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentalId;
import mk.ukim.finki.emt.rentalmanagement.domain.repository.PaymentRepository;
import mk.ukim.finki.emt.rentalmanagement.service.PaymentService;
import mk.ukim.finki.emt.rentalmanagement.service.RentalService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.PaymentForm;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final RentalService rentalService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, RentalService rentalService) {
        this.paymentRepository = paymentRepository;
        this.rentalService = rentalService;
    }

    @Override
    public Payment save(PaymentForm paymentForm) {
        RentalId rentalId = paymentForm.getRentalId();
        Rental rental = rentalService.findById(rentalId);
        Money totalAmount = rentalService.totalAmount(rental);
        Payment payment = new Payment(rentalId, paymentForm.getPaymentMethod(), totalAmount);
        return paymentRepository.save(payment);
    }

}
