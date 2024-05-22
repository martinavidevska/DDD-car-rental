package mk.ukim.finki.emt.rentalmanagement.domain.exceptions;


import mk.ukim.finki.emt.rentalmanagement.domain.models.RentalId;

public class RentingNotFoundException extends RuntimeException{
    public RentingNotFoundException(RentalId id ) {
        super(String.format("The renting with id %d is not found",id));
    }
}
