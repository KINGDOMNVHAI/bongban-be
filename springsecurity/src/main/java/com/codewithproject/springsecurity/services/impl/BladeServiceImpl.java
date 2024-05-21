package com.codewithproject.springsecurity.services.impl;

import com.codewithproject.springsecurity.dto.request.InsertBladeLineRequest;
import com.codewithproject.springsecurity.dto.entitydto.BladeDto;
import com.codewithproject.springsecurity.dto.response.BladeListResponse;
import com.codewithproject.springsecurity.entities.Blade;
import com.codewithproject.springsecurity.entities.BladeUnit;
import com.codewithproject.springsecurity.entities.Brand;
import com.codewithproject.springsecurity.entities.Line;
import com.codewithproject.springsecurity.repository.BladeRepository;
import com.codewithproject.springsecurity.repository.BladeUnitRepository;
import com.codewithproject.springsecurity.repository.BrandRepository;
import com.codewithproject.springsecurity.repository.LineRepository;
import com.codewithproject.springsecurity.services.BladeService;
import com.codewithproject.springsecurity.util.DateUtil;
import jakarta.persistence.Column;
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
    private LineServiceImpl lineServiceImpl;

    @Autowired
    private BrandRepository brandRepo;

    @Autowired
    private BladeRepository bladeRepo;

    @Autowired
    private BladeUnitRepository bladeUnitRepo;

    @Autowired
    private LineRepository lineRepo;

    public List<BladeListResponse> getListBlade() {
        List<BladeListResponse> resultItemDtoList = new ArrayList<>();
        List<Object[]> resultList = bladeRepo.getListBladeLine();

        if (!resultList.isEmpty()) {
            for (Object[] object : resultList) {
                BladeListResponse dto = new BladeListResponse();
                dto = dto.convertObjectToDto(object);
                Integer count = lineServiceImpl.countByBladeCD(dto.getBladeCD());
                dto.setCountRegister(count);
                resultItemDtoList.add(dto);
            }
        }
        return resultItemDtoList;
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

    // Insert Blade, Line, Line Progress
    public Map<String,Object> insertBladeLine(InsertBladeLineRequest req) {
        Map<String,Object> result = new HashMap<>();

        // Find brand
        String brandCd = req.getBrandCD();
        Optional<Brand> brandOptional = brandRepo.getBrandDetail(brandCd);

        if (brandOptional.isEmpty()) {
            result.put(MAP_STATUS_CODE, 404);
            result.put(MAP_MESSAGE, MESS_SAVE_FAILED);
            return result;
        }
        Brand brandDetail = brandOptional.get();

        // Count blade_cd in table blade_unit, find unit_id
        String unitID = req.getBladeCD();
        Integer cntBladeCD = bladeUnitRepo.countByBladeCD(req.getBladeCD());
        if (cntBladeCD == 0) {
            unitID = unitID + "_00001";
        } else {
            unitID = unitID + "_0000" + cntBladeCD;
        }

        Blade blade = new Blade();
        blade.setBladeName(req.getBladeName());
        blade.setBladeCD(req.getBladeCD());
        blade.setBladeFullName(req.getBladeFullName());

        blade.setBrandName(brandDetail.getBrandName());
        blade.setBrandCD(brandDetail.getBrandCD());
        blade.setSubBranch(req.getSubBranch());

        blade.setPaddleTP(req.getPaddleTP());
        bladeRepo.save(blade);

        BladeUnit bladeUnit = new BladeUnit();
        bladeUnit.setUnitId(unitID);
        bladeUnit.setBladeCD(req.getBladeCD());
        bladeUnitRepo.save(bladeUnit);

        // Get line id
        Integer count = lineServiceImpl.countByBladeCD(req.getBladeCD());
        System.out.println(count);
        Integer year = DateUtil.getThisYear();
        Integer month = DateUtil.getThisMonth();
        String monthStr = month.toString();
        if (month < 10) {
            monthStr = "0" + monthStr;
        }
        String lineID = year.toString() + "_" + monthStr + "_" + DateUtil.giveZeroBeforeNumber("00000", count + 1);

        Line line = new Line();
        line.setBladeUnitID(unitID);
        line.setId(lineID);
        line.setPeriod(req.getPeriod());
        line.setPeriodCnt(req.getPeriodCnt());
        line.setInitPrice(req.getInitPrice());
        line.setDeposit(req.getDeposit());
        line.setDepreciation(req.getDepreciation());
        line.setFee(req.getFee());
        line.setEndPrice(req.getEndPrice());
        lineRepo.save(line);

        result.put(MAP_STATUS_CODE, 400);
        result.put(MAP_MESSAGE, MESS_SAVE_SUCCESS);
        return result;
    }
}
