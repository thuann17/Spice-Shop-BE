package com.system.spice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SpiceDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 100)
    String name;
    String description;
    @NotNull
    BigDecimal price;
    @NotNull
    @Size(max = 50)
    String unit;
    Integer quantityAvailable;
    Boolean status;
    Integer position;

}