package com.codewithproject.springsecurity.dto.entitydto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineProgressDto {
    private String lineID;
    private String userEmail;
    private String name;
    private String depositStatus;
}
