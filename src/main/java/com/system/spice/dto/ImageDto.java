package com.system.spice.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

@Data
public class ImageDto implements Serializable {
    Integer id;
    @Size(max = 225)
    String urlImage;
    Instant createAt;
    Boolean status;
    SpiceDto spice;
}