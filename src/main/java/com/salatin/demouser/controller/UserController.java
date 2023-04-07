package com.salatin.demouser.controller;

import com.salatin.demouser.model.User;
import com.salatin.demouser.model.dto.request.UserRegistrationRequestDto;
import com.salatin.demouser.model.dto.response.UserRegistrationResponseDto;
import com.salatin.demouser.service.UserService;
import com.salatin.demouser.service.mapper.UserMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<UserRegistrationResponseDto> register(
        @RequestBody UserRegistrationRequestDto requestDto) {
        User userToSave = userMapper.toModel(requestDto, passwordEncoder);
        User savedUser = userService.save(userToSave);

        return new ResponseEntity<>(userMapper.toDto(savedUser), HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("/by-age")
    public ResponseEntity<List<UserRegistrationResponseDto>> getAllWithAgeGreaterThan(
        @NonNull @RequestParam short minAge) {
        List<UserRegistrationResponseDto> result =
            userService.getUsersWithAgeGte(minAge).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/by-article-color")
    public ResponseEntity<List<UserRegistrationResponseDto>> getAllWithArticleColor(
        @NonNull @RequestParam String articleColor
    ) {
        List<UserRegistrationResponseDto> result =
            userService.getAllWithArticleColor(articleColor).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/names/by-article-count")
    public ResponseEntity<List<String>> findAllNamesByArticleCountGte(
        @RequestParam(name = "moreThan", defaultValue = "3") int count
    ) {
        List<String> nameslist = userService.getAllUserNamesWithArticleCountGte(count);

        return new ResponseEntity<>(nameslist, HttpStatus.OK);
    }
}
