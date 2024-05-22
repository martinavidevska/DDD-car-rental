package mk.ukim.finki.emt.vehiclemanagement.domain.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

@Embeddable
@Getter
public class  LicensePlate implements ValueObject {

    private final String licensePlate;

    public LicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    protected LicensePlate (){
        this.licensePlate =null;
    }
}
