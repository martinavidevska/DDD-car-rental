package mk.ukim.finki.emt.rentalmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.validation.constraints.NotNull;

@Embeddable
public class VehicleId extends DomainObjectId {

    public VehicleId() {
        super(VehicleId.randomId(VehicleId.class).getId());
    }

    @JsonCreator
    public VehicleId(@NotNull String uuid) {
        super(uuid);
    }

    public static VehicleId of(String uuid) {
        return new VehicleId(uuid);
    }

    @JsonValue
    public String getId() {
        return super.getId();
    }
}
