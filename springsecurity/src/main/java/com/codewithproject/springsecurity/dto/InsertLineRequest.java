package com.codewithproject.springsecurity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
