package com.salatin.demouser.service.mapper;

import com.salatin.demouser.model.Article;
import com.salatin.demouser.model.User;
import com.salatin.demouser.model.dto.request.UserRegistrationRequestDto;
import com.salatin.demouser.model.dto.response.UserResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password",
        expression = "java(encodePassword(requestDto.getPassword(), passwordEncoder))")
    @Mapping(target = "articles", expression = "java(createList())")
    User toModel(UserRegistrationRequestDto requestDto, PasswordEncoder passwordEncoder);

    @Mapping(target = "articleIds", source = "articles", qualifiedByName = "articlesToIds")
    UserResponseDto toDto(User user);

    @Named("encodePassword")
    default String encodePassword(String password, PasswordEncoder encoder) {
        return encoder.encode(password);
    }

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
