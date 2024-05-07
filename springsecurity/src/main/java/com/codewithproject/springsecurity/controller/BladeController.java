package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.dto.entitydto.BladeDto;
import com.codewithproject.springsecurity.services.impl.BladeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/blade")
@RequiredArgsConstructor
public class BladeController {

    @Autowired
    private BladeServiceImpl bladeServiceImpl;

    @GetMapping("/list")
    public List<BladeDto> getListBrand() {
        return bladeServiceImpl.getListBlade();
    }
}
