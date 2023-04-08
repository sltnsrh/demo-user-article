package com.salatin.demouser.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponseDto {
    private String token;
    private String refreshToken;
}
