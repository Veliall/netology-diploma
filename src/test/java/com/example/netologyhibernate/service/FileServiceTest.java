package com.example.netologyhibernate.service;

import com.example.netologyhibernate.dto.request.FilenameUpdateDto;
import com.example.netologyhibernate.entity.FileEntity;
import com.example.netologyhibernate.excteption.AppException;
import com.example.netologyhibernate.repository.FileRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Igor Khristiuk on 18.01.2022
 */

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @InjectMocks
    FileService fileService;
    @Mock
    FileRepo fileRepo;

    @Test
    void getFileWithoutProblem() {
        String filename = "TEST";
        byte[] bytes = "test".getBytes();
        FileEntity entity = new FileEntity("TEST", LocalDateTime.now(), bytes, 1L, null);
        when(fileRepo.findByFilename(filename)).thenReturn(Optional.of(entity));

        byte[] file = fileService.getFile(filename);

        verify(fileRepo, times(1)).findByFilename(filename);
        assertEquals(bytes, file);
    }

    @Test
    void getFileWithException() {
        String filename = "TEST";
        when(fileRepo.findByFilename(filename)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fileService.getFile(filename)).isInstanceOf(AppException.class);
        verify(fileRepo, times(1)).findByFilename(filename);
    }

    @Test
    void deleteFile() {
        String filename = "TEST";
        fileService.deleteFile(filename);
        verify(fileRepo, times(1)).deleteByFilename(filename);
    }

    @Test
    void updateFilename() {
        String filename = "TEST";
        String newName = "NEW";
        byte[] bytes = "test".getBytes();
        FileEntity entity = new FileEntity("TEST", LocalDateTime.now(), bytes, 1L, null);
        when(fileRepo.updateFilename(filename, newName)).thenReturn(Optional.of(entity));

        Optional<FileEntity> fileEntity = fileService.updateFilename(filename, new FilenameUpdateDto(newName));

        verify(fileRepo, times(1)).updateFilename(filename, newName);
        assertEquals(entity, fileEntity.get());
    }

    @Test
    void updateFilenameWithExc() {
        String filename = "TEST";
        String newName = "NEW";
        when(fileRepo.updateFilename(filename, newName)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fileService.updateFilename(filename, new FilenameUpdateDto(newName))).isInstanceOf(AppException.class);
        verify(fileRepo, times(1)).updateFilename(filename, newName);
    }


    @Test
    void getListShouldThrowException() {
        int limit = 3;
        assertThatThrownBy(() -> fileService.getList("token", limit)).isInstanceOf(AppException.class);
    }
}