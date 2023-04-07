package com.salatin.demouser.model.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationResponseDto {
    private Long id;
    private String name;
    private short age;
    private String email;
    private List<Long> articleIds;
}
