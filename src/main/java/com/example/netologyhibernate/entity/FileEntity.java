package com.example.netologyhibernate.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Igor Khristiuk on 08.01.2022
 */
@Entity(name = "files")
@Getter
@Setter
@NoArgsConstructor
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String filename;
    private String hash;
    private byte[] file;
    private Long size;

    public FileEntity(String filename, String hash, byte[] file, Long size) {
        this.filename = filename;
        this.hash = hash;
        this.file = file;
        this.size = size;
    }
}
