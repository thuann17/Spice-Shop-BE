package com.system.spice.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.system.spice.entity.ServiceEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.spice.entity.ServiceDetail;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String image;
    private List<ServiceDetailDTO> details;

    public ServiceDTO(ServiceEntity service) {
        this.id = service.getId();
        this.name = service.getName();
        this.price = service.getPrice();
        this.image = service.getImage();
     
        this.details = service.getDetails() != null
                ? service.getDetails().stream().map(ServiceDetailDTO::new).collect(Collectors.toList())
                : new ArrayList<>();

    }
}

@Getter
@Setter
class ServiceDetailDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;

    public ServiceDetailDTO(ServiceDetail detail) {
        this.id = detail.getId();
        this.name = detail.getName();
        this.price = detail.getPrice();
        this.quantity = detail.getQuantity();
    }
}
