package com.example.netologyhibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Igor Khristiuk on 08.01.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    private String hash;
    private MultipartFile file;
}
