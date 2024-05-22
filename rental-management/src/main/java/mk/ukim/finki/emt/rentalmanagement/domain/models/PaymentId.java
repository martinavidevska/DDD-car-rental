package mk.ukim.finki.emt.rentalmanagement.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class PaymentId extends DomainObjectId {
    private PaymentId() {
        super(PaymentId.randomId(PaymentId.class).getId());
    }

    public PaymentId(String uuid) {
        super(uuid);
    }

}
