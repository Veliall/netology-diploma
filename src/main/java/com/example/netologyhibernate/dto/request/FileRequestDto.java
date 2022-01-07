package com.example.netologyhibernate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Igor Khristiuk on 08.01.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileRequestDto {
    private String hash;
    private byte[] file;
}
