package com.system.spice.controller.manager;

import com.system.spice.dto.SpiceDto;
import com.system.spice.model.manager.SpiceModel;
import com.system.spice.service.manager.SpiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerSpiceController {
    @Autowired
    private SpiceService spiceService;

    //    API lấy danh sách gia vị
    @GetMapping("/get-all-spice")
    public ResponseEntity<List<SpiceDto>> getAllSpices() {
        return ResponseEntity.ok().body(spiceService.getAllSpices());
    }

    @PostMapping("/add-spice")
    public ResponseEntity<SpiceDto> addSpice(@RequestBody SpiceModel model) {
        return ResponseEntity.ok().body(spiceService.addSpice(model));
    }
}
