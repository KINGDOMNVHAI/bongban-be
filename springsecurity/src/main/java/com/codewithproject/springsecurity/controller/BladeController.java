package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.dto.request.InsertBladeLineRequest;
import com.codewithproject.springsecurity.dto.entitydto.BladeDto;
import com.codewithproject.springsecurity.dto.request.SearchBladeRequest;
import com.codewithproject.springsecurity.dto.response.BladeListResponse;
import com.codewithproject.springsecurity.services.impl.BladeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BladeController {

    @Autowired
    private BladeServiceImpl bladeServiceImpl;

    @GetMapping("/public/blade/list")
    public List<BladeListResponse> getListBrand() {
        return bladeServiceImpl.getListBlade();
    }

    @PostMapping("/public/blade/search")
    public List<BladeListResponse> searchListBlade(@RequestBody SearchBladeRequest req) {
        return bladeServiceImpl.searchListBlade(req);
    }

    @GetMapping("/public/blade/list-blade/{bladeCD}")
    public List<BladeDto> getListByBladeCD(@PathVariable String bladeCD) {
        return bladeServiceImpl.getListByBladeCD(bladeCD);
    }

    @PostMapping("/public/blade/insert")
    public Map<String,Object> insertBlade(@RequestBody InsertBladeLineRequest req) {
        return bladeServiceImpl.insertBladeLine(req);
    }
}
