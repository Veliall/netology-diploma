package com.example.netologyhibernate.service;

import com.example.netologyhibernate.dto.request.FileDto;
import com.example.netologyhibernate.dto.request.FilenameUpdateDto;
import com.example.netologyhibernate.dto.response.FileListResponseDto;
import com.example.netologyhibernate.entity.FileEntity;
import com.example.netologyhibernate.excteption.AppException;
import com.example.netologyhibernate.repository.FileRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

///**
// * @author Igor Khristiuk on 18.01.2022
// */
//
//@ExtendWith(MockitoExtension.class)
//class FileServiceTest {
//
//    @InjectMocks
//    FileService fileService;
//    @Mock
//    FileRepo fileRepo;
//
//    @Test
//    void getFileWithoutProblem() {
//        String filename = "TEST";
//        byte[] bytes = "test".getBytes();
//        FileEntity entity = new FileEntity("TEST", "test", bytes, 1L);
//        when(fileRepo.findByFilename(filename)).thenReturn(Optional.of(entity));
//
//        byte[] file = fileService.getFile(filename);
//
//        verify(fileRepo, times(1)).findByFilename(filename);
//        assertEquals(bytes, file);
//    }
//
//    @Test
//    void getFileWithException() {
//        String filename = "TEST";
//        when(fileRepo.findByFilename(filename)).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> fileService.getFile(filename)).isInstanceOf(AppException.class);
//        verify(fileRepo, times(1)).findByFilename(filename);
//    }
//
//    @Test
//    void deleteFile() {
//        String filename = "TEST";
//        fileService.deleteFile(filename);
//        verify(fileRepo, times(1)).deleteByFilename(filename);
//    }
//
//    @Test
//    void updateFilename() {
//        String filename = "TEST";
//        String newName = "NEW";
//        FileEntity entity = new FileEntity("NEW", "test", null, 1L);
//        when(fileRepo.updateFilename(filename, newName)).thenReturn(Optional.of(entity));
//
//        Optional<FileEntity> fileEntity = fileService.updateFilename(filename, new FilenameUpdateDto(newName));
//
//        verify(fileRepo, times(1)).updateFilename(filename, newName);
//        assertEquals(entity, fileEntity.get());
//    }
//
//    @Test
//    void updateFilenameWithExc() {
//        String filename = "TEST";
//        String newName = "NEW";
//        when(fileRepo.updateFilename(filename, newName)).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> fileService.updateFilename(filename, new FilenameUpdateDto(newName))).isInstanceOf(AppException.class);
//        verify(fileRepo, times(1)).updateFilename(filename, newName);
//    }
//
//    @Test
//    void getList() {
//        int limit = 3;
//        Pageable pageable = PageRequest.of(0, limit);
//        FileEntity entity = new FileEntity("NEW", "test", null, 1L);
//        FileListResponseDto dto = new FileListResponseDto("NEW", 1L);
//        List<FileListResponseDto> dtos = new ArrayList<>();
//        List<FileEntity> fileEntities = new ArrayList<>();
//        for (int i = 0; i < limit; i++) {
//            fileEntities.add(entity);
//            dtos.add(dto);
//        }
//        Page<FileEntity> page = new PageImpl<>(fileEntities);
//        when(fileRepo.findAll(pageable)).thenReturn(page);
//
//        List<FileListResponseDto> list = fileService.getList(limit);
//        verify(fileRepo, times(1)).findAll(pageable);
//        assertEquals(dtos, list);
//    }
//}