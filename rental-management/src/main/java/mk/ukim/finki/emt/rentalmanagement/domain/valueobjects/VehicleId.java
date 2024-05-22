package mk.ukim.finki.emt.rentalmanagement.domain.valueobjects;

import javax.validation.constraints.NotNull;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class VehicleId extends DomainObjectId {
    private VehicleId() {
        super(VehicleId.randomId(VehicleId.class).getId());
    }

    public VehicleId(@NotNull String uuid) {
        super(uuid);
    }

    public static VehicleId of(String uuid) {
        return new VehicleId(uuid);
    }
}
