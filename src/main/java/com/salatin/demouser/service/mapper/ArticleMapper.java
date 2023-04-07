package com.salatin.demouser.service.mapper;

import com.salatin.demouser.model.Article;
import com.salatin.demouser.model.Color;
import com.salatin.demouser.model.dto.request.ArticleCreationRequestDto;
import com.salatin.demouser.model.dto.response.ArticleCreationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "color", source = "color", qualifiedByName = "toEnumColor")
    Article toModel(ArticleCreationRequestDto requestDto);

    ArticleCreationResponseDto toDto(Article article);

    @Named("toEnumColor")
    default Color toEnumColor(String color) {
        return Color.valueOf(color.toUpperCase());
    }
}
