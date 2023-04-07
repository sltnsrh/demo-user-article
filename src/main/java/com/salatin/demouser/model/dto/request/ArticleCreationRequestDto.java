package com.salatin.demouser.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCreationRequestDto {
    private String text;
    private String color;
}
