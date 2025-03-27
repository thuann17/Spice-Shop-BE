package com.system.spice.dto;

import com.system.spice.entity.SpiceDetail;
import lombok.Data;

@Data
public class SpiceDetailDto {
    private String description;
    private String ingredients;
    private String color;
    private String usageInstructions;
    private String storage;
    private String smell;
    private String expirationPeriod;
    private String packagingLocation;

    public SpiceDetailDto(SpiceDetail detail) {
        this.description = detail.getDescription();
        this.ingredients = detail.getIngredients();
        this.color = detail.getColor();
        this.usageInstructions = detail.getUsageInstructions();
        this.storage = detail.getStorage();
        this.smell = detail.getSmell();
        this.expirationPeriod = detail.getExpirationPeriod();
        this.packagingLocation = detail.getPackagingLocation();
    }
}
