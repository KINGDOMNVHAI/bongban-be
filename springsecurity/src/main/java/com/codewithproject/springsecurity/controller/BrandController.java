package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.dto.entitydto.BrandDto;
import com.codewithproject.springsecurity.dto.request.SearchBrandRequest;
import com.codewithproject.springsecurity.dto.response.BrandListResponse;
import com.codewithproject.springsecurity.services.impl.BrandServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BrandController {

    @Autowired
    private BrandServiceImpl brandServiceImpl;

    @GetMapping("/public/brand/list")
    public List<BrandDto> getListBrand() {
        return brandServiceImpl.getListBrand();
    }

    @GetMapping("/public/brand/list-root")
    public List<BrandDto> getListBrandParent() {
        return brandServiceImpl.getListBrandCheckParent(false, null);
    }

    @GetMapping("/public/brand/list-sub/{brandCD}")
    public List<BrandDto> getListSubBrand(
            @PathVariable String brandCD
    ) {
        return brandServiceImpl.getListBrandCheckParent(true, brandCD);
    }

    @PostMapping("/public/brand/search")
    public List<BrandListResponse> searchListBrand(@RequestBody SearchBrandRequest req) {
        return brandServiceImpl.searchListBrand(req);
    }
}
