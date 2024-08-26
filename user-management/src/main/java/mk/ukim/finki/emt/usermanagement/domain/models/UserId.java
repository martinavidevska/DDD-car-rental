package mk.ukim.finki.emt.usermanagement.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.validation.constraints.NotNull;

public class UserId extends DomainObjectId {
    protected UserId() {
        super(DomainObjectId.randomId(UserId.class).getId());
    }

    public UserId(@NotNull String uuid) {
        super(uuid);
    }
    public static UserId of(String uuid) {
        return new UserId(uuid);
    }

}
