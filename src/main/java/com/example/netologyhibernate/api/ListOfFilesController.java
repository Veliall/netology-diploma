package com.example.netologyhibernate.api;

import com.example.netologyhibernate.dto.response.FileListResponseDto;
import com.example.netologyhibernate.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Igor Khristiuk on 07.01.2022
 */

@RestController()
@RequestMapping("/list")
@RequiredArgsConstructor
public class ListOfFilesController {
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<FileListResponseDto>> getList(@RequestHeader("auth-token") String authToken, @RequestParam Integer limit) {

        List<FileListResponseDto> dtos = fileService.getList(authToken, limit);

        return ResponseEntity.ok().body(dtos);
    }

}
