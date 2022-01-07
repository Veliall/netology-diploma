package com.example.netologyhibernate.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Igor Khristiuk on 07.01.2022
 */

@RestController()
@RequestMapping("/list")
@RequiredArgsConstructor
public class ListOfFilesController {


    @GetMapping
    public ResponseEntity getList(@RequestParam Integer limit) {

        //TODO: get list with limit

        return ResponseEntity.ok().body(null);
    }

}
