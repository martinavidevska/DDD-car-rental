package mk.ukim.finki.emt.rentalmanagement.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

@Entity
@Table(name="location")
@Getter
@NoArgsConstructor
public class Location extends AbstractEntity<LocationId> {
    private String name;
    private String city;
    private String state;
    private String contact;

    public Location(String name, String city, String state, String contact) {
        super(LocationId.randomId(LocationId.class));
        this.name = name;
        this.city = city;
        this.state = state;
        this.contact = contact;
    }

}
