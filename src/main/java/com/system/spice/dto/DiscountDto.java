package com.system.spice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
public class DiscountDto implements Serializable {
    Integer id;
    @NotNull
    BigDecimal discountValue;
    Instant startDate;
    Instant endDate;
}