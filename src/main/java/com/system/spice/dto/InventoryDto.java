package com.system.spice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class InventoryDto implements Serializable {
    Integer id;
    @NotNull
    Integer quantity;
    @NotNull
    BigDecimal purchasePrice;
    Instant purchaseDate;
}