package com.codewithproject.springsecurity.dto.response;

import com.codewithproject.springsecurity.config.Constants;
import com.codewithproject.springsecurity.util.DateUtil;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BladeListResponse {

    // tb_bb_blade
    private Integer seq;
    private String brandCD;
    private String brandName;

    private String bladeCD;
    private String bladeName;
    private String bladeFullName;

    // tb_bb_line
    private String period;
    private Integer periodCnt;
    private Integer initPrice;
    private Integer deposit;
    private Integer depreciation;
    private Integer fee;
    private Integer endPrice;

    // Others
    private Integer countRegister;

    public BladeListResponse convertObjectToDto(Object[] object) {
        BladeListResponse dto = new BladeListResponse();
        dto.setSeq((Integer) object[0]);
        dto.setBrandCD((String) object[1]);
        dto.setBrandName((String) object[2]);
        dto.setBladeCD((String) object[3]);
        dto.setBladeName((String) object[4]);
        dto.setBladeFullName((String) object[5]);
        dto.setPeriod((String) object[9]);
        dto.setPeriodCnt((Integer) object[10]);
        dto.setInitPrice((Integer) object[11]);
        dto.setDeposit((Integer) object[12]);
        dto.setDepreciation((Integer) object[13]);
        dto.setFee((Integer) object[14]);
        dto.setEndPrice((Integer) object[15]);
        return dto;
    }
}
