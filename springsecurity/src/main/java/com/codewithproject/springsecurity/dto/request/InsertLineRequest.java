package com.codewithproject.springsecurity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertLineRequest {

    // Insert Line
    private String id;

    private String bladeUnitID;

    private Integer periodCnt;

    private String period;

    private Integer initPrice;

    private Integer deposit;

    private Integer depreciation;

    private Integer fee;

    private Integer endPrice;

    // Register Line
    private String token; // check user login or not

    private String lineID;

    private String email;

    private String depositStatus;
}
