package com.salatin.demouser.controller;

import com.salatin.demouser.model.dto.request.UserLoginRequestDto;
import com.salatin.demouser.model.dto.response.UserLoginResponseDto;
import com.salatin.demouser.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(
        @RequestBody UserLoginRequestDto requestDto
        ) {

        return new ResponseEntity<>(authService.login(requestDto), HttpStatus.OK);
    }
}
