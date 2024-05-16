package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.dto.entitydto.BrandDto;
import com.codewithproject.springsecurity.model.ResponseModel;
import com.codewithproject.springsecurity.services.impl.BrandServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.codewithproject.springsecurity.config.MessageConstants.*;

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
        return brandServiceImpl.getListBrandCheckParent(false);
    }

    @GetMapping("/public/brand/list-sub")
    public List<BrandDto> getListSubBrand() {
        return brandServiceImpl.getListBrandCheckParent(true);
    }
}
