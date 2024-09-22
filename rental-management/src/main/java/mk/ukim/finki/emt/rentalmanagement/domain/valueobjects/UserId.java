package mk.ukim.finki.emt.rentalmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.validation.constraints.NotNull;

public class UserId extends DomainObjectId {
    private UserId() {
        super(UserId.randomId(UserId.class).getId());
    }

    @JsonCreator
    public UserId(@NotNull String uuid) {
        super(uuid);
    }

    // Factory method
    public static UserId of(String uuid) {
        return new UserId(uuid);
    }

    // String representation
    @JsonValue
    public String getId() {
        return super.getId();
    }
}
