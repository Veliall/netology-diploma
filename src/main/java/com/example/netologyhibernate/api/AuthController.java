package com.example.netologyhibernate.api;

import com.example.netologyhibernate.dto.request.LoginRequestDto;
import com.example.netologyhibernate.dto.response.LoginResponseDto;
import com.example.netologyhibernate.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Igor Khristiuk on 07.01.2022
 */

@RestController()
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        return authenticationService.login(dto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authToken) {
        authenticationService.logout(authToken);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
