package com.salatin.demouser.controller;

import com.salatin.demouser.model.User;
import com.salatin.demouser.model.dto.request.UserLoginRequestDto;
import com.salatin.demouser.model.dto.request.UserRegistrationRequestDto;
import com.salatin.demouser.model.dto.response.UserLoginResponseDto;
import com.salatin.demouser.model.dto.response.UserResponseDto;
import com.salatin.demouser.service.AuthService;
import com.salatin.demouser.service.mapper.UserMapper;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
        @RequestBody @Valid UserRegistrationRequestDto requestDto) {

        User userToSave = userMapper.toModel(requestDto, passwordEncoder);
        User savedUser = authService.register(userToSave);

        return new ResponseEntity<>(userMapper.toDto(savedUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(
        @RequestBody UserLoginRequestDto requestDto
        ) {

        return new ResponseEntity<>(authService.login(requestDto), HttpStatus.OK);
    }
}
