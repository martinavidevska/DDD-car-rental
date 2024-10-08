package mk.ukim.finki.emt.rentalmanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;


@Embeddable
public class LocationId extends DomainObjectId {

    public LocationId() {
        super(LocationId.randomId(LocationId.class).getId());
    }

    @JsonCreator
    public LocationId(String id) {
        super(id);
    }

    @JsonValue
    public String getId() {
        return super.getId();
    }

    public static LocationId of(String id) {
        return new LocationId(id);
    }
}
