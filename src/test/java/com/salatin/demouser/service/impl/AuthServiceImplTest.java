package com.salatin.demouser.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.salatin.demouser.exception.UserAlreadyExistsException;
import com.salatin.demouser.model.User;
import com.salatin.demouser.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    private static final String TEST_MAIL = "test@mail.com";
    private static final String TEST_PASSWORD = "password";

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserService userService;
    private User userWithEmailAndPassword;

    @BeforeEach
    void create() {
        userWithEmailAndPassword = new User();
        userWithEmailAndPassword.setEmail(TEST_MAIL);
        userWithEmailAndPassword.setPassword(TEST_PASSWORD);
    }

    @Test
    void givenNotExistingUser_whenRegister_thenReturnCreatedUser() {
        when(userService.findByEmail(TEST_MAIL)).thenReturn(null);
        when(userService.save(userWithEmailAndPassword))
            .thenReturn(userWithEmailAndPassword);

        User actual = authService.register(userWithEmailAndPassword);

        assertNotNull(actual);
    }

    @Test
    void givenExistingUser_whenRegister_thenThrowsUserAlreadyExistsException() {
        when(userService.findByEmail(TEST_MAIL)).thenReturn(new User());

        assertThrows(UserAlreadyExistsException.class,
            () -> authService.register(userWithEmailAndPassword));
    }
}
