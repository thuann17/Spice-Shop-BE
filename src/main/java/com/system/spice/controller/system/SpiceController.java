package com.system.spice.controller.system;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.system.spice.dto.SpiceDto;
import com.system.spice.entity.SpiceDetail;
import com.system.spice.service.manager.SpiceService;
import java.util.List;

@RestController
@RequestMapping("api/spices")
public class SpiceController {
    
    private final SpiceService spiceService;

    public SpiceController(SpiceService spiceService) {
        this.spiceService = spiceService;
    }

    // Lấy tất cả giavij
    @GetMapping
    public List<SpiceDto> getAllSpices() {
        return spiceService.getAllSpices();
    }
    
    // chi tieetrs gia vị   
    @GetMapping("/{id}")
    public ResponseEntity<SpiceDto> getSpiceById(@PathVariable Long id) {
        return ResponseEntity.ok(spiceService.getSpiceById(id));
    }


}
