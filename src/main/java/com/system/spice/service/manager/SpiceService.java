package com.system.spice.service.manager;

import com.system.spice.dto.SpiceDto;
import com.system.spice.entity.Spice;
import com.system.spice.model.manager.SpiceModel;
import com.system.spice.repository.SpiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpiceService {
    @Autowired
    private SpiceRepository spiceRepository;

    //  Lấy danh sách gia vị trong db
    public List<SpiceDto> getAllSpices() {
        List<Spice> spices = spiceRepository.findAll();
        System.out.println("Count of Spices: " + spices.size());
        return convertSpiceListToDto(spices);
    }

    //   Thêm spice
    public SpiceDto addSpice(SpiceModel model) {
        Spice spice = new Spice();
        spice.setName(model.getNameSpice());
        spice.setPrice(model.getPrice());
        spice.setUnit(model.getUnit());
        List<Spice> spices = spiceRepository.findAll();
        System.out.println("Count of Spices: " + spices.size());
        return convertSpiceToDto(spiceRepository.save(spice));
    }

    //    Convert dto spice
    public SpiceDto convertSpiceToDto(Spice spice) {
        SpiceDto spiceDto = new SpiceDto();
        spiceDto.setId(spice.getId());
        spiceDto.setDescription(spice.getDescription());
        spiceDto.setName(spice.getName());
        spiceDto.setPrice(spice.getPrice());
        spiceDto.setUnit(spice.getUnit());
        spiceDto.setPosition(spice.getPosition());
        spiceDto.setQuantityAvailable(spice.getQuantityAvailable());
        return spiceDto;
    }

    //    Convert list dto spice
    public List<SpiceDto> convertSpiceListToDto(List<Spice> spiceList) {
        return spiceList.stream().map(this::convertSpiceToDto).collect(Collectors.toList());
    }
}
