package org.jalau.at18.searchobject;

import org.jalau.at18.searchobject.service.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class SearchobjectApplication implements CommandLineRunner {
    @Resource
    FilesStorageService storageService;
    UnzipFile unZipper;

    public static void main(String[] args) {
        SpringApplication.run(SearchobjectApplication.class, args);
    }

    // it can receive N parameters of type String.
    @Override
    public void run(String... arg) throws Exception {
        unZipper.ExecuteUnZip();
        storageService.init();
    }
}


