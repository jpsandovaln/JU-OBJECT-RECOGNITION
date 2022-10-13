package org.jalau.at18.searchobject.service;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
    public void init();

    public Path save(MultipartFile file);

    // public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
