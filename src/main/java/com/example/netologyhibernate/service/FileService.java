package com.example.netologyhibernate.service;

import com.example.netologyhibernate.dto.request.FileDto;
import com.example.netologyhibernate.dto.request.FilenameUpdateDto;
import com.example.netologyhibernate.dto.response.FileListResponseDto;
import com.example.netologyhibernate.entity.FileEntity;
import com.example.netologyhibernate.excteption.AppException;
import com.example.netologyhibernate.repository.FileRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Igor Khristiuk on 08.01.2022
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {
    private final FileRepo fileRepo;

    public FileEntity save(String filename, FileDto dto) {
        try {
            byte[] file = dto.getFile().getBytes();
            Long size = dto.getFile().getSize();
            return fileRepo.save(new FileEntity(filename, dto.getHash(), file, size));
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    public byte[] getFile(String filename) {
        Optional<FileEntity> entity = fileRepo.findByFilename(filename);
        if (entity.isPresent()) {
            FileEntity fileEntity = entity.get();
            return fileEntity.getFile();
        } else {
            throw new AppException("File with this name doesn't exist");
        }
    }

    @Transactional
    public void deleteFile(String filename) {
        fileRepo.deleteByFilename(filename);
    }

    @Transactional
    public Optional<FileEntity> updateFilename(String filename, FilenameUpdateDto dto) {
        Optional<FileEntity> fileEntity = fileRepo.updateFilename(filename, dto.getFilename());
        if (fileEntity.isPresent()) {
            return fileEntity;
        } else {
            throw new AppException("File with this name doesn't exist");
        }

    }

    public List<FileListResponseDto> getList(Integer limit) {
        Pageable pageable = PageRequest.of(0, limit);
        Page<FileEntity> entityPage = fileRepo.findAll(pageable);
        return convertToDto(entityPage);
    }

    private List<FileListResponseDto> convertToDto(Page<FileEntity> entityPage) {
        return entityPage.stream()
                .map(o -> new FileListResponseDto(o.getFilename(), o.getSize()))
                .collect(Collectors.toList());
    }
}
