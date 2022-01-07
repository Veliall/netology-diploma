package com.example.netologyhibernate.api;

import com.example.netologyhibernate.dto.request.FilenameUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author Igor Khristiuk on 07.01.2022
 */

@RestController()
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    @GetMapping
    public ResponseEntity getFile(@RequestParam String filename) {

        //TODO: get file

        return ResponseEntity.ok().body(null);
    }

    @PostMapping(
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity uploadFile(@RequestParam String filename, @RequestBody MultipartFile file) {

        //TODO: upload

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateFile(@RequestParam String filename, @RequestBody FilenameUpdateDto dto) {

        //TODO: update name

        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping
    public ResponseEntity deleteFile(@RequestParam @NotNull String filename) {

        //TODO: delete

        return ResponseEntity.ok().body(null);
    }
}
