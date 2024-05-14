package com.codewithproject.springsecurity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsertLineProgressRequest {

    private String lineID;

    private String userEmail;

    private String depositStatus;
}
