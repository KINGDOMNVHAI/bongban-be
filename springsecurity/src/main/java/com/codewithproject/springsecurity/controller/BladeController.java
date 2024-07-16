package com.codewithproject.springsecurity.controller;

import com.codewithproject.springsecurity.config.MessageConstants;
import com.codewithproject.springsecurity.dto.request.InsertBladeLineRequest;
import com.codewithproject.springsecurity.dto.entitydto.BladeDto;
import com.codewithproject.springsecurity.dto.request.RegisterBladeRequest;
import com.codewithproject.springsecurity.dto.request.SearchBladeRequest;
import com.codewithproject.springsecurity.dto.response.BladeListResponse;
import com.codewithproject.springsecurity.services.impl.BladeServiceImpl;
import com.codewithproject.springsecurity.services.impl.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BladeController {

    @Autowired
    private BladeServiceImpl bladeServiceImpl;

    @Autowired
    private FileServiceImpl fileServiceImpl;

    @GetMapping("/public/blade/list")
    public List<BladeListResponse> getListBrand() {
        return bladeServiceImpl.getListBlade();
    }

    @PostMapping("/public/blade/search")
    public List<BladeListResponse> searchListBlade(@RequestBody SearchBladeRequest req) {
        return bladeServiceImpl.searchListBlade(req);
    }

//    @GetMapping("/public/blade/list-blade/{bladeCD}")
//    public List<BladeDto> getListByBladeCD(@PathVariable String bladeCD) {
//        return bladeServiceImpl.getListByBladeCD(bladeCD);
//    }

    @GetMapping("/public/blade/detail/{unitID}")
    public List<BladeDto> getBladeByUnitID(@PathVariable String unitID) {
        return bladeServiceImpl.getBladeByUnitID(unitID);
    }

    @PostMapping("/public/blade/insert")
    public Map<String,Object> insertBlade(@RequestBody InsertBladeLineRequest req) {
        return bladeServiceImpl.insertBladeLine(req);
    }

    @PostMapping("/public/blade/upload-image")
    public Map<String,Object> uploadImageBlade(@RequestParam MultipartFile[] files) throws IOException {
        Map<String,Object> result = new HashMap<>();
        String type = "img";
        if (files == null) {
            result.put("success", false);
            result.put("message", MessageConstants.MESS_TYPE_OR_FILE_EMPTY);
            return result;
        }
        String message = fileServiceImpl.storeFile(type, files);

        result.put("message", message);
        return result;
    }

    @PostMapping("/public/blade/register")
    public String registerBlade(@RequestBody RegisterBladeRequest req) {
        return "blade/register";
    }
}
