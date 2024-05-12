package com.codewithproject.springsecurity.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertLineRequest {

    private String id;

    private String bladeUnitID;

    private String periodCnt;

    private String period;

    private Integer initPrice;

    private Integer deposit;

    private Integer depreciation;

    private Integer fee;

    private Integer endPrice;

    private String lineID;

    private String userEmail;

    private String depositStatus;
}
