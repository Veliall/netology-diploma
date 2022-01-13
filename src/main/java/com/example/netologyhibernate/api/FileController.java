package com.example.netologyhibernate.api;

import com.example.netologyhibernate.dto.request.FileDto;
import com.example.netologyhibernate.dto.request.FilenameUpdateDto;
import com.example.netologyhibernate.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author Igor Khristiuk on 07.01.2022
 */

@RestController()
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping()
    public ResponseEntity<Resource> getFile(@RequestParam String filename) {
        byte[] file = fileService.getFile(filename);
        return ResponseEntity.ok().body(new ByteArrayResource(file));
    }

    @PostMapping(
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity uploadFile(@RequestParam String filename, FileDto file) {
        fileService.save(filename, file);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateFile(@RequestParam String filename, @RequestBody FilenameUpdateDto dto) {
        fileService.updateFilename(filename, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteFile(@RequestParam @NotNull String filename) {
        fileService.deleteFile(filename);
        return ResponseEntity.ok().build();
    }
}
