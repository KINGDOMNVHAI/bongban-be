package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.request.InsertBladeLineRequest;
import com.codewithproject.springsecurity.dto.entitydto.BladeDto;
import com.codewithproject.springsecurity.entities.Blade;
import com.codewithproject.springsecurity.entities.Brand;
import com.codewithproject.springsecurity.repository.BladeRepository;
import com.codewithproject.springsecurity.repository.BrandRepository;
import com.codewithproject.springsecurity.services.BladeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.codewithproject.springsecurity.config.MessageConstants.*;

@Service
public class BladeServiceImpl implements BladeService {

    @Autowired
    private BladeRepository bladeRepo;

    @Autowired
    private BrandRepository brandRepo;

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

    public List<BladeDto> getListByBladeCD(String bladeCD) {
        List<BladeDto> resultItemDtoList = new ArrayList<>();
        List<Object[]> resultList = bladeRepo.getListByBladeCD(bladeCD);
        for (Object[] object : resultList) {
            BladeDto resultItemDto = new BladeDto();
            resultItemDtoList.add(resultItemDto.convertObjectToDto(object));
        }
        return resultItemDtoList;
    }

    public Map<String,Object> insertBladeLine(InsertBladeLineRequest req) {
        Map<String,Object> result = new HashMap<>();

        // Find brand
        String brandCd = req.getBrandCD();
        Optional<Brand> brandOptional = brandRepo.getBrandDetail(brandCd);

        if (brandOptional.isEmpty()) {
            result.put(MAP_STATUS_CODE, 404);
            result.put(MAP_MESSAGE, SAVE_FAILED);
            return result;
        }

        Brand brandDetail = brandOptional.get();
        Blade blade = new Blade();
        blade.setBladeName(req.getBladeName());
        blade.setBladeCD(req.getBladeCD());
        blade.setBladeFullName(req.getBladeFullName());

        blade.setBrandName(brandDetail.getBrandName());
        blade.setBrandCD(brandDetail.getBrandCD());
        blade.setSubBranch(req.getSubBranch());

        blade.setPaddleTP(req.getPaddleTP());
        bladeRepo.save(blade);

        result.put(MAP_STATUS_CODE, 400);
        result.put(MAP_MESSAGE, SAVE_SUCCESS);
        return result;
    }
}
