package mk.ukim.finki.emt.sharedkernel.domain.financial;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import java.util.Objects;

@Embeddable
@Getter
public class Money implements ValueObject {

    private Currency currency;
    private double amount;

    protected Money(){
        this.amount=0;
        this.currency=null;
    }
    public Money(@NonNull Currency currency, @NonNull double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static Money valueOf(Currency currency, int amount) {
        return new Money(currency,amount);
    }
    public Money add(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency,amount + money.amount);
    }

    public Money subtract(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency,amount - money.amount);
    }

    public Money multiply(int m)  {
        return new Money(currency,amount*m);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0 && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }
}
