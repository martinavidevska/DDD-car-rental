package mk.ukim.finki.emt.rentalmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Vehicle implements ValueObject {
    private final VehicleId vehicleId;
    private final Money dailyPrice;
    private final boolean available;

    public Vehicle() {
        this.vehicleId=VehicleId.randomId(VehicleId.class);
        this.dailyPrice=Money.valueOf(Currency.MKD,0);
        this.available=true;
    }



    @JsonCreator
    public Vehicle(@JsonProperty("id") VehicleId vehicleId,
                   @JsonProperty("dailyPrice")Money dailyPrice,
                   @JsonProperty("available")boolean available) {
        this.vehicleId = vehicleId;
        this.dailyPrice = dailyPrice;
        this.available=available;
    }
}
