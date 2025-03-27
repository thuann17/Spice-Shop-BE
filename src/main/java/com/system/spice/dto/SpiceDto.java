package com.system.spice.dto;

import com.system.spice.entity.Image;
import com.system.spice.entity.Spice;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SpiceDto implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private String unit;
    private Integer position;

    private Integer quantityAvailable;
    private Boolean status;
    private List<String> imageUrls;
    private SpiceDetailDto spiceDetail; // Thêm chi tiết gia vị

    public SpiceDto(Spice spice) {
        this.id = spice.getId();
        this.name = spice.getName();
        this.description = spice.getDescription();
        this.price = spice.getPrice();
        this.unit = spice.getUnit();
        this.position = spice.getPosition();
        this.quantityAvailable = spice.getQuantityAvailable();
        this.status = spice.getStatus();
        this.imageUrls = spice.getImages().stream().map(Image::getUrl).toList();
    }

}