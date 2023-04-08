package com.salatin.demouser.service.impl;

import com.salatin.demouser.model.User;
import com.salatin.demouser.model.dto.request.UserLoginRequestDto;
import com.salatin.demouser.model.dto.response.UserLoginResponseDto;
import com.salatin.demouser.service.AuthService;
import com.salatin.demouser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        String email = requestDto.getEmail();
        User user = userService.findByEmail(email);

        if (user == null) {
            throw new RuntimeException();
        }

        String password = requestDto.getPassword();

        authenticate(email, password);

        return null;
    }

    private void authenticate(String email, String password) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Bad credentials, try again");
        }
    }
}
