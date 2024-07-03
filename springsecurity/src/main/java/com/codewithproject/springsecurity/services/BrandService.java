package com.codewithproject.springsecurity.services;

import com.codewithproject.springsecurity.dto.entitydto.BrandDto;
import com.codewithproject.springsecurity.dto.request.SearchBrandRequest;
import com.codewithproject.springsecurity.dto.response.BrandListResponse;

import java.util.List;

public interface BrandService {

    List<BrandDto> getListBrand();

    List<BrandListResponse> searchListBrand(SearchBrandRequest req);

    List<BrandDto> getListBrandCheckParent(boolean hasParent, String brandCD);
}
