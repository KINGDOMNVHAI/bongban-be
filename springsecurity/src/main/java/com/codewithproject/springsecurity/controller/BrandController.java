package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.dto.entitydto.BrandDto;
import com.codewithproject.springsecurity.services.impl.BrandServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/brand")
@RequiredArgsConstructor
public class BrandController {

    @Autowired
    private BrandServiceImpl brandServiceImpl;

    @GetMapping("/list")
    public List<BrandDto> getListBrand() {
        return brandServiceImpl.getListBrand();
    }
}
