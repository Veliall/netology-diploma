package com.example.netologyhibernate.service;

import com.example.netologyhibernate.repository.AuthenticationRepository;
import com.example.netologyhibernate.security.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;

import static com.example.netologyhibernate.service.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Igor Khristiuk on 18.01.2022
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationRepository authenticationRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private UserService userService;

    @Test
    void login() {
        Mockito.when(userService.loadUserByUsername(USERNAME_1)).thenReturn(USER_1);
        Mockito.when(jwtTokenUtil.generateToken(USER_1)).thenReturn(TOKEN_1);
        assertEquals(LOGIN_RESPONSE_DTO, authenticationService.login(LOGIN_REQUEST_DTO));
        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(USERNAME_PASSWORD_AUTHENTICATION_TOKEN);
    }

    @Test
    void logout() {
        Mockito.when(authenticationRepository.getUsernameByToken(BEARER_TOKEN_SUBSTRING_7)).thenReturn(USERNAME_1);
        authenticationService.logout(BEARER_TOKEN);
        Mockito.verify(authenticationRepository, Mockito.times(1)).getUsernameByToken(BEARER_TOKEN_SUBSTRING_7);
        Mockito.verify(authenticationRepository, Mockito.times(1)).removeTokenAndUsernameByToken(BEARER_TOKEN_SUBSTRING_7);
    }
}