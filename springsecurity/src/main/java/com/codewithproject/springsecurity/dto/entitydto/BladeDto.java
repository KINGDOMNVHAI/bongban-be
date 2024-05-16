package com.codewithproject.springsecurity.dto.entitydto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BladeDto {

    private Integer seq;

    private String brandCD;

    private String brandName;

    private String bladeCD;

    private String bladeName;

    private String bladeFullName;

    private String subBranch;

    private String paddleTP;

    // blade_unit
    private String unitID;

    private String inspectPercent;

    public BladeDto convertObjectToDto(Object[] object) {
        BladeDto dto = new BladeDto();
        dto.setSeq((Integer) object[0]);
        dto.setBrandCD((String) object[1]);
        dto.setBrandName((String) object[2]);
        dto.setBladeName((String) object[3]);
        dto.setBladeCD((String) object[4]);
        dto.setBladeFullName((String) object[5]);
        dto.setSubBranch((String) object[6]);
        dto.setPaddleTP((String) object[7]);
        dto.setUnitID((String) object[8]);
        dto.setInspectPercent((String) object[9]);
//        dto.setCreatedDateCom(DateUtility.toDateString((Date) object[3], Constants.FORMAT_YYYY_MM_DD_HH_MM_SS));
        return dto;
    }
}
