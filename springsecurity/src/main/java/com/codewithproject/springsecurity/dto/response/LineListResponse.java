package com.codewithproject.springsecurity.dto.response;

import com.codewithproject.springsecurity.dto.entitydto.LineProgressDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LineListResponse {

    // line table
    private String id;

    private String bladeUnitID;

    private Integer periodCnt;

    private String period;

    private Integer initPrice;

    private Integer deposit;

    private Integer depreciation;

    private Integer fee;

    private Integer endPrice;

    // line_progress table
    private List<LineProgressDto> listProgress;
    private Integer countListProgress;
}
