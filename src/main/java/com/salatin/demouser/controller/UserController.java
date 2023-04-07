package com.salatin.demouser.controller;

import com.salatin.demouser.model.User;
import com.salatin.demouser.model.dto.request.UserRegistrationRequestDto;
import com.salatin.demouser.model.dto.response.UserRegistrationResponseDto;
import com.salatin.demouser.service.UserService;
import com.salatin.demouser.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
