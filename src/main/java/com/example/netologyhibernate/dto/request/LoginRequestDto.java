package com.example.netologyhibernate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Igor Khristiuk on 08.01.2022
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank (message = "Login is required")
    private String login;
    @NotBlank (message = "Password is required")
    private String password;
}
