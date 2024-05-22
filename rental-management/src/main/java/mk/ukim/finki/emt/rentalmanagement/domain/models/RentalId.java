package mk.ukim.finki.emt.rentalmanagement.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class RentalId extends DomainObjectId {
    private RentalId() {
        super(RentalId.randomId(RentalId.class).getId());
    }

    public RentalId(@NonNull String uuid) {
        super(uuid);
    }

}
