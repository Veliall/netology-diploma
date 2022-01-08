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

    public FileEntity(String filename, String hash, byte[] file) {
        this.filename = filename;
        this.hash = hash;
        this.file = file;
    }
}
