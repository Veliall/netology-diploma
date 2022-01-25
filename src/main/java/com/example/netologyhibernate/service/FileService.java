package com.example.netologyhibernate.service;

import com.example.netologyhibernate.dto.request.FileDto;
import com.example.netologyhibernate.dto.request.FilenameUpdateDto;
import com.example.netologyhibernate.dto.response.FileListResponseDto;
import com.example.netologyhibernate.entity.FileEntity;
import com.example.netologyhibernate.entity.User;
import com.example.netologyhibernate.excteption.AppException;
import com.example.netologyhibernate.repository.AuthenticationRepository;
import com.example.netologyhibernate.repository.FileRepo;
import com.example.netologyhibernate.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
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
    private final UsersRepo userRepository;
    private final AuthenticationRepository authenticationRepository;


    public boolean save(String authToken,String filename, FileDto dto) {
        MultipartFile file = dto.getFile();
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Upload file: Unauthorized");
            throw new AppException("Upload file: Unauthorized");
        }

        try {
            fileRepo.save(new FileEntity(filename,
                    LocalDateTime.now(),
                    file.getBytes(),
                    file.getSize(),
                    user));
            log.info("Success upload file. User {}", user.getUsername());
            return true;
        } catch (IOException e) {
            log.error("Upload file: Input data exception");
            throw new AppException("Upload file: Input data exception");
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

    public List<FileListResponseDto> getList(String authToken, Integer limit) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Get all files: Unauthorized");
            throw new AppException("Get all files: Unauthorized");
        }
        log.info("Success get all files. User {}", user.getUsername());
        return fileRepo.findAllByUser(user).stream()
                .map(o -> new FileListResponseDto(o.getFilename(), o.getSize()))
                .collect(Collectors.toList());
    }

    private List<FileListResponseDto> convertToDto(Page<FileEntity> entityPage) {
        return entityPage.stream()
                .map(o -> new FileListResponseDto(o.getFilename(), o.getSize()))
                .collect(Collectors.toList());
    }

    private User getUserByAuthToken(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            final String authTokenWithoutBearer = authToken.split(" ")[1];
            final String username = authenticationRepository.getUsernameByToken(authTokenWithoutBearer);
            return userRepository.findByUsername(username);
        }
        return null;
    }
}
