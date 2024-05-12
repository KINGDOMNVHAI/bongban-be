package com.codewithproject.springsecurity.dto.entitydto;

import com.codewithproject.springsecurity.entities.Brand;
import com.codewithproject.springsecurity.entities.Line;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineDto {

    private String id;

    private String bladeUnitID;

    private String periodCnt;

    private String period;

    private Integer initPrice;

    private Integer deposit;

    private Integer depreciation;

    private Integer fee;

    private Integer endPrice;

    public Line convertToEntity() {
        Line entity = new Line();
        entity.setId(this.id);
        entity.setBladeUnitID(this.bladeUnitID);
        entity.setPeriodCnt(this.periodCnt);
        entity.setPeriod(this.period);
        entity.setInitPrice(this.initPrice);
        entity.setDeposit(this.deposit);
        entity.setDepreciation(this.depreciation);
        entity.setFee(this.fee);
        entity.setEndPrice(this.endPrice);
        return entity;
    }
}
