package mk.ukim.finki.emt.rentalmanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;




public class LocationId extends DomainObjectId {

    private LocationId() {
        super(LocationId.randomId(LocationId.class).getId());
    }

    @JsonCreator
    public LocationId(String id) {
        super(id);
    }

    public static LocationId of(String id) {
        return new LocationId(id);
    }
}

