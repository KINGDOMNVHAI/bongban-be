package com.codewithproject.springsecurity.dto.entitydto;

import com.codewithproject.springsecurity.entities.Brand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDto {

    private String brandCD;

    private Integer seq;

    private String brandName;

    private String parent;

    public Brand convertToEntity() {
        Brand entity = new Brand();
        entity.setSeq(this.seq);
        entity.setBrandCD(this.brandCD);
        entity.setBrandName(this.brandName);
        entity.setParent(this.parent);
        return entity;
    }
}
