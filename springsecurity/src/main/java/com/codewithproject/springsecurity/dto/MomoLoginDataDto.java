package com.codewithproject.springsecurity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class MomoLoginDataDto {

    private Integer status;
    private String message;
    private Map<String, Object> data;
//    private String username;
//    private String token;
}
