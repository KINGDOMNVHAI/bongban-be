package com.codewithproject.springsecurity.dto.entitydto;

import com.codewithproject.springsecurity.entities.Blade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BladeDto {

    private Integer seq;

    private String brandCD;

    private String brandName;

    public Blade convertToEntity() {
        Blade entity = new Blade();
        entity.setSeq(this.seq);
        entity.setBrandCD(this.brandCD);
        entity.setBrandName(this.brandName);
        return entity;
    }
}
