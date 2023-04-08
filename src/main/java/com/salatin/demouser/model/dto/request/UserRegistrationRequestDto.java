package com.salatin.demouser.model.dto.request;

import com.salatin.demouser.model.dto.validation.FieldsValueMatch;
import com.salatin.demouser.model.dto.validation.ValidEmail;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FieldsValueMatch(
    field = "password",
    fieldMatch = "repeatPassword",
    message = "Passwords do not match")
public class UserRegistrationRequestDto {
    @Size(min = 3, message = "Your name length must be at list 3 symbols")
    private String name;
    @Min(value = 18, message = "You must be at list 18 years old")
    private short age;
    @ValidEmail
    private String email;
    private String password;
    private String repeatPassword;
}
