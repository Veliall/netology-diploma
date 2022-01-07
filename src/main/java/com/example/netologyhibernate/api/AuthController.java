package com.example.netologyhibernate.api;

import com.example.netologyhibernate.dto.request.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Igor Khristiuk on 07.01.2022
 */

@RestController()
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {

        //TODO: authorization

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity logout() {

        //TODO: logout

        return ResponseEntity.ok().build();
    }
}
