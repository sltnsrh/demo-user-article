package com.salatin.demouser.model.dto.request;

import com.salatin.demouser.model.dto.validation.FieldsValueMatch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FieldsValueMatch(
    field = "password",
    fieldMatch = "repeatPassword",
    message = "Passwords do not match")
public class UserRegistrationRequestDto {
    private String name;
    private short age;
    private String email;
    private String password;
    private String repeatPassword;
}
