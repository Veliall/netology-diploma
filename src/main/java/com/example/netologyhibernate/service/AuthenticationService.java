package com.example.netologyhibernate.service;

import com.example.netologyhibernate.dto.request.LoginRequestDto;
import com.example.netologyhibernate.dto.response.LoginResponseDto;
import com.example.netologyhibernate.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Igor Khristiuk on 11.01.2022
 */
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    private static final Map<String, String> TOKENS = new ConcurrentHashMap<>();

    public LoginResponseDto login(LoginRequestDto request) {
        final String username = request.getLogin();
        final String password = request.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final UserDetails userDetails = userService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        TOKENS.put(token, username);
        return new LoginResponseDto(token);
    }

    public void logout(String authToken) {
        final String token = authToken.substring(7);
        final String username = TOKENS.get(token);
        System.out.println("User " + username + " logout. JWT is disabled.");
        TOKENS.remove(token);
    }
}
