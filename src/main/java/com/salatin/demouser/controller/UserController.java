package com.salatin.demouser.controller;

import com.salatin.demouser.model.dto.response.UserResponseDto;
import com.salatin.demouser.service.UserService;
import com.salatin.demouser.service.mapper.UserMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @RequestMapping("/by-min-age")
    public ResponseEntity<List<UserResponseDto>> getAllWithAgeGreaterThan(
        @NonNull @RequestParam short minAge) {
        List<UserResponseDto> usersList =
            userService.getUsersWithAgeGte(minAge).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/by-article-color")
    public ResponseEntity<List<UserResponseDto>> getAllWithArticleColor(
        @NonNull @RequestParam String articleColor
    ) {
        List<UserResponseDto> usersList =
            userService.getAllWithArticleColor(articleColor).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/names/by-min-article-count")
    public ResponseEntity<List<String>> findAllNamesByArticleCountGte(
        @RequestParam(name = "moreThan", defaultValue = "3") int count
    ) {
        List<String> namesList = userService.getAllUserNamesWithArticleCountGte(count);

        return new ResponseEntity<>(namesList, HttpStatus.OK);
    }
}
