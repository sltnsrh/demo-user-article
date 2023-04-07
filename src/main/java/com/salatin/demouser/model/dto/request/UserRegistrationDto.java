package com.salatin.demouser.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
    private String name;
    private short age;
    private String email;
    private String password;
    private String repeatPassword;
}
