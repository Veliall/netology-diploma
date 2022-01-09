package com.example.netologyhibernate.api;

import com.example.netologyhibernate.dto.response.FileListResponseDto;
import com.example.netologyhibernate.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<FileListResponseDto>> getList(@RequestParam Integer limit) {

        List<FileListResponseDto> dtos = fileService.getList(limit);

        return ResponseEntity.ok().body(dtos);
    }

}
