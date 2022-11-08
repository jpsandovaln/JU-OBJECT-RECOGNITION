package org.jalau.at18.searchobject.controller.service;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import org.jalau.at18.searchobject.common.exception.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
/**
 * It is responsable of process the service
 *
 * @author Maria Hurtado
 * @version 1.0
 */

@Service
public class FilesStorageServiceImpl implements FilesStorageService{
    private final Path root = Paths.get("uploads");
    @Override
    public void init() {
        try {
            if(!Files.exists(root)){
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new FileStorageException("Could not initialize folder for upload!");
        }
    }


    public Path save(MultipartFile file) {
        String file1 = StringUtils.cleanPath(file.getOriginalFilename());
        String framesPath = String.valueOf(System.currentTimeMillis());
        try {
            Files.createDirectory(Paths.get("uploads", framesPath));
            Path uploadsPath = Paths.get("uploads", framesPath, file.getOriginalFilename());
            Files.copy(file.getInputStream(), uploadsPath);
            return  uploadsPath;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new FileStorageException("Could not load the files!");
        }
    }
}
