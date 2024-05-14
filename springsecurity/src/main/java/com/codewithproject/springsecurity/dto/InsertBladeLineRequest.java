package com.codewithproject.springsecurity.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertBladeLineRequest extends InsertLineRequest {

    private Integer seq;

    private String brandCD;

    private String brandName;

    private String bladeCD;

    private String bladeName;

    private String bladeFullName;

    private String subBranch;

    private String paddleTP;
}
