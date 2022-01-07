package com.example.netologyhibernate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Igor Khristiuk on 08.01.2022
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    private String login;
    private String password;
}
