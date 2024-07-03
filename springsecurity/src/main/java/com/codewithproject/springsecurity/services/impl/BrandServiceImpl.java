package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.entitydto.BrandDto;
import com.codewithproject.springsecurity.dto.request.SearchBladeRequest;
import com.codewithproject.springsecurity.dto.request.SearchBrandRequest;
import com.codewithproject.springsecurity.dto.response.BladeListResponse;
import com.codewithproject.springsecurity.dto.response.BrandListResponse;
import com.codewithproject.springsecurity.entities.Brand;
import com.codewithproject.springsecurity.repository.BrandRepository;
import com.codewithproject.springsecurity.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

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

    public List<BrandListResponse> searchListBrand(SearchBrandRequest req) {
        String brandCD = req.getBrandCD() != null ? req.getBrandCD() : "";
        List<Object[]> resultList = brandRepo.searchListBrand(brandCD);
        return setBrandListResponse(resultList);
    }

    public List<BrandDto> getListBrandCheckParent(boolean hasParent, String brandCD) {
        List<BrandDto> result = new ArrayList<>();
        List<Brand> listBrand = new ArrayList<>();
        if (hasParent) {
            listBrand = brandRepo.getListSubBrandByBrandCD(brandCD);
        } else {
            listBrand = brandRepo.getListBrandParentRoot();
        }

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

    private List<BrandListResponse> setBrandListResponse(List<Object[]> resultList) {
        List<BrandListResponse> resultItemDtoList = new ArrayList<>();
        if (!resultList.isEmpty()) {
            for (Object[] object : resultList) {
                BrandListResponse dto = new BrandListResponse();
                dto = dto.convertObjectToDto(object);
//                Integer count = lineServiceImpl.countByBladeCD(dto.getBladeCD());
//                dto.setCountRegister(count);
                resultItemDtoList.add(dto);
            }
        }
        return resultItemDtoList;
    }
}
