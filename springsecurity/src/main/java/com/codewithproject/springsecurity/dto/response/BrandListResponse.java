package com.codewithproject.springsecurity.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandListResponse {

    // tb_bb_brand
    private Integer seq;
    private String brandCD;
    private String brandName;
    private String parent;
    private String thumbnail;

    public BrandListResponse convertObjectToDto(Object[] object) {
        BrandListResponse dto = new BrandListResponse();
        dto.setSeq((Integer) object[0]);
        dto.setBrandCD((String) object[1]);
        dto.setBrandName((String) object[2]);
        dto.setParent((String) object[3]);
        dto.setThumbnail((String) object[4]);
        return dto;
    }
}
