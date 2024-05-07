package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.entitydto.BrandDto;
import com.codewithproject.springsecurity.entities.Brand;
import com.codewithproject.springsecurity.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl {

    @Autowired
    private BrandRepository brandRepo;

    public List<BrandDto> getListBrand() {
        List<BrandDto> result = new ArrayList<>();
        List<Brand> listBrand = brandRepo.getListBrand();
        if (!listBrand.isEmpty()) {
            result = listBrand.stream().map(b -> {
                BrandDto dto = new BrandDto();
                dto.setSeq(b.getSeq());
                dto.setBrandCD(b.getBrandCD());
                dto.setBrandName(b.getBrandName());
                dto.setParent(b.getParent());
                return dto;
            }).collect(Collectors.toList());
        }
        return result;
    }
}
