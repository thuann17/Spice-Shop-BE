package com.system.spice.model.manager;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpiceModel {
    String nameSpice;
    BigDecimal price;
    Boolean isStatus;
    String description;
    Integer quantityAvailable;
    String unit;
}
