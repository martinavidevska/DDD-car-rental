package mk.ukim.finki.emt.rentalmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class UserId extends DomainObjectId {

    @JsonCreator
    public UserId(String id) {
        super(id); // Pass the string to the parent class constructor
    }

    public UserId(UserId userId) {
        super(userId.getId());
    }

    @JsonValue
    public String getId() {
        return super.getId();
    }
}
