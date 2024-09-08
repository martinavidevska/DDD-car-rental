package mk.ukim.finki.emt.rentalmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.validation.constraints.NotNull;

public class VehicleId extends DomainObjectId {

    // Default constructor for frameworks like JPA (if needed)
    private VehicleId() {
        super(VehicleId.randomId(VehicleId.class).getId());
    }

    // Constructor to initialize VehicleId with a UUID string
    @JsonCreator
    public VehicleId(@NotNull String uuid) {
        super(uuid);
    }

    // Static factory method to create a VehicleId from a string
    public static VehicleId of(String uuid) {
        return new VehicleId(uuid);
    }

    // Method to return the ID string (used for serialization)
    @JsonValue
    public String getId() {
        return super.getId();
    }
}
