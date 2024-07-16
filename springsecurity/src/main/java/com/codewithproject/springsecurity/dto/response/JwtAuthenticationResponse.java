package com.codewithproject.springsecurity.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {

    private String emailOrUsername;

    private String token;

    private String refreshToken;

    private String message;
}
