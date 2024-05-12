package com.codewithproject.springsecurity.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertLineProgressRequest {

    private String lineID;

    private String userEmail;

    private String depositStatus;
}
