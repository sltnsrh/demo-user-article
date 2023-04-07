package com.salatin.demouser.service.mapper;

import com.salatin.demouser.model.Article;
import com.salatin.demouser.model.User;
import com.salatin.demouser.model.dto.request.UserRegistrationRequestDto;
import com.salatin.demouser.model.dto.response.UserRegistrationResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "articles", expression = "java(createList())")
    User toModel(UserRegistrationRequestDto requestDto);

    @Mapping(target = "articleIds", source = "articles", qualifiedByName = "articlesToIds")
    UserRegistrationResponseDto toDto(User user);

    default List<Article> createList() {
        return new ArrayList<>();
    }

    @Named("articlesToIds")
    default List<Long> articlesToIds(List<Article> articles) {
        return articles.stream()
            .map(Article::getId)
            .collect(Collectors.toList());
    }
}
