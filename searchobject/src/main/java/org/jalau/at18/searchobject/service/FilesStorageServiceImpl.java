package org.jalau.at18.searchobject.service;

import org.jalau.at18.searchobject.exception.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


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
