package com.codewithproject.springsecurity.services;

import com.codewithproject.springsecurity.dto.entitydto.BladeDto;
import com.codewithproject.springsecurity.dto.entitydto.BrandDto;

import java.util.List;

public interface BrandService {

    List<BrandDto> getListBrand();

    List<BrandDto> getListBrandCheckParent(boolean hasParent);
}
