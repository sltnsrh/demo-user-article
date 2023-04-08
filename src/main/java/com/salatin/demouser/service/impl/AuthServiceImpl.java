package com.salatin.demouser.service.impl;

import com.salatin.demouser.exception.UserAlreadyExistsException;
import com.salatin.demouser.model.User;
import com.salatin.demouser.model.dto.request.UserLoginRequestDto;
import com.salatin.demouser.model.dto.response.UserLoginResponseDto;
import com.salatin.demouser.security.JwtTokenProvider;
import com.salatin.demouser.service.AuthService;
import com.salatin.demouser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public User register(User user) {
        checkIfUserExists(user);

        return userService.save(user);
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        authenticate(email, password);

        String token = jwtTokenProvider.createToken(email);

        return new UserLoginResponseDto(token);
    }

    private void checkIfUserExists(User user) {
        User userFromDb = userService.findByEmail(user.getEmail());

        if (userFromDb !=null) {
            throw new UserAlreadyExistsException(
                String.format("User with email %s is already exists", user.getEmail()));
        }
    }

    private void authenticate(String email, String password) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad credentials, try again");
        }
    }
}
