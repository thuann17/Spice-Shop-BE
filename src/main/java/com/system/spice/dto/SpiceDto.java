package com.system.spice.dto;

import com.system.spice.entity.Image;
import com.system.spice.entity.Spice;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
}