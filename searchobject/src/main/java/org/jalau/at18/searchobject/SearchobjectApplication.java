package org.jalau.at18.searchobject;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprietary information of Jala University
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jala University
 */
import org.jalau.at18.searchobject.controller.service.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.annotation.Resource;

@ServletComponentScan //we need this annotation to register @WebFilter (middleware package)
@SpringBootApplication
public class SearchobjectApplication implements CommandLineRunner {
    @Resource
    FilesStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(SearchobjectApplication.class, args);
    }

    // it can receive N parameters of type String.
    @Override
    public void run(String... arg) throws Exception {
        storageService.init();
    }
}


