package mk.ukim.finki.emt.rentalmanagement.api.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.rentalmanagement.domain.models.Payment;
import mk.ukim.finki.emt.rentalmanagement.domain.models.RentalId;
import mk.ukim.finki.emt.rentalmanagement.service.PaymentService;
import mk.ukim.finki.emt.rentalmanagement.service.forms.PaymentForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @PostMapping("/")
    private ResponseEntity<Payment> addPayment(@RequestBody PaymentForm paymentForm) {
        Payment payment = this.paymentService.save(paymentForm);
        return ResponseEntity.ok(payment);
    }
}
