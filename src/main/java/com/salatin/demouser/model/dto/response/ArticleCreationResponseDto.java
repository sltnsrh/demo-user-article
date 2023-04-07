package com.salatin.demouser.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCreationResponseDto {
    private Long id;
    private String text;
    private String color;
}
