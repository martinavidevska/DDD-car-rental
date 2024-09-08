package mk.ukim.finki.emt.vehiclemanagement.services.forms;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.vehiclemanagement.domain.models.VehicleType;
import mk.ukim.finki.emt.vehiclemanagement.domain.valueobjects.LicensePlate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class VehicleForm {

    @NotBlank(message = "License plate cannot be blank")
    private String licensePlate;

    private Integer amount;

    private String currency;

    @NotBlank(message = "Model cannot be blank")
    private String model;

    @NotBlank(message = "Brand cannot be blank")
    private String brand;

    @NotNull(message = "Seats cannot be null")
    @Min(value = 1, message = "Seats must be at least 1")
    private Integer seats;

    @NotNull(message = "Bags cannot be null")
    @Min(value = 0, message = "Bags must be at least 0")
    private Integer bags;

    @NotNull(message = "Vehicle type cannot be null")
    private VehicleType vehicleType;

    @NotBlank(message = "Picture link cannot be blank")
    private String pictureLink;
}
