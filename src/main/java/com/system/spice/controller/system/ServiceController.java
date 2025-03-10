package com.system.spice.controller.system;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.spice.entity.ServiceEntity;
import com.system.spice.repository.ServiceRepository;
import com.system.spice.dto.ServiceDTO;

@RestController
@RequestMapping("/api/services")

public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> services = serviceRepository.findAllWithDetails() // Gọi phương thức đã định nghĩa
            .stream()
            .map(ServiceDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(services);
    }


}
