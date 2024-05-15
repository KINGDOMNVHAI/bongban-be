package com.codewithproject.springsecurity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ResponseDto {
    private Integer status;
    private String message;
    private Map<String, Object> data;
}
