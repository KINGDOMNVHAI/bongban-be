package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.entitydto.BladeDto;
import com.codewithproject.springsecurity.entities.Blade;
import com.codewithproject.springsecurity.repository.BladeRepository;
import com.codewithproject.springsecurity.services.BladeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BladeServiceImpl implements BladeService {

    @Autowired
    private BladeRepository bladeRepo;

    public List<BladeDto> getListBlade() {
        List<BladeDto> result = new ArrayList<>();
        List<Blade> listBrand = bladeRepo.getListBlade();
        if (!listBrand.isEmpty()) {
            result = listBrand.stream().map(b -> {
                BladeDto dto = new BladeDto();
                dto.setSeq(b.getSeq());
                dto.setBrandCD(b.getBrandCD());
                dto.setBrandName(b.getBrandName());
                return dto;
            }).collect(Collectors.toList());
        }
        return result;
    }
}
