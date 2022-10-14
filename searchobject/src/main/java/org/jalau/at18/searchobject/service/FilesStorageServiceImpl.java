package org.jalau.at18.searchobject.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
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
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public Path save(MultipartFile file) {
        String framesPath = String.valueOf(System.currentTimeMillis());
        try {
            Files.createDirectory(Paths.get("uploads", framesPath));
            Path uploadsPath = Paths.get("uploads", framesPath, file.getOriginalFilename());
            Files.copy(file.getInputStream(), uploadsPath);
            return Paths.get(root.toAbsolutePath().toFile().getAbsolutePath(), file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
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
            throw new RuntimeException("Could not load the files!");
        }
    }
}
