package com.example.netologyhibernate.service;

import ch.qos.logback.core.util.ContentTypeUtil;
import com.example.netologyhibernate.dto.FileDto;
import com.example.netologyhibernate.dto.request.FilenameUpdateDto;
import com.example.netologyhibernate.entity.FileEntity;
import com.example.netologyhibernate.excteption.AppException;
import com.example.netologyhibernate.repository.FileRepo;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author Igor Khristiuk on 08.01.2022
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {
    private final FileRepo fileRepo;

    public void save(String filename, FileDto dto) {
        try {
            byte[] file = dto.getFile().getBytes();
            fileRepo.save(new FileEntity(filename, dto.getHash(), file));
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    @SneakyThrows
    public FileDto getFile(String filename) {
        Optional<FileEntity> entity = fileRepo.findByFilename(filename);
        FileDto dto = new FileDto();
        if (entity.isPresent()) {
            FileEntity fileEntity = entity.get();
            InputStream inputStream = new ByteArrayInputStream(fileEntity.getFile());
            MultipartFile file = new MockMultipartFile(fileEntity.getFilename(), filename, "application octet stream", inputStream);
            dto.setHash(fileEntity.getHash());
            dto.setFile(file);
            return dto;
        } else {
            throw new AppException("File with this name doesn't exist");
        }
    }

    @Transactional
    public void deleteFile(String filename) {
        fileRepo.deleteByFilename(filename);
    }

    @Transactional
    public void updateFilename(String filename, FilenameUpdateDto dto) {
        fileRepo.updateFilename(filename, dto.getFilename());
    }
}