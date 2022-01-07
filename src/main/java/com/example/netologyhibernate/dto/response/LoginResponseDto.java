package com.example.netologyhibernate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Igor Khristiuk on 08.01.2022
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
}
