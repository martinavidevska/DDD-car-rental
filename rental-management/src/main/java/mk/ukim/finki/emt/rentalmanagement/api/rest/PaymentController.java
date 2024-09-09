package mk.ukim.finki.emt.rentalmanagement.api.rest;

import mk.ukim.finki.emt.rentalmanagement.domain.models.Payment;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentalId;
import mk.ukim.finki.emt.rentalmanagement.service.PaymentService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.PaymentForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    private ResponseEntity<Payment> addPayment(@RequestBody PaymentForm paymentForm) {
        return ResponseEntity.ok(paymentService.save(paymentForm));
    }
}
