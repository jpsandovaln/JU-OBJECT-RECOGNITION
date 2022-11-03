package org.jalau.at18.searchobject.controller.service;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.stream.Stream;
/**
 * It is responsable of storage the service
 *
 * @author Maria Hurtado
 * @version 1.0
 */
public interface FilesStorageService {
    //initiate the method
    public void init();

    //save the path
    public Path save(MultipartFile file);

    // delete image
    public void deleteAll();
    //load all
    public Stream<Path> loadAll();
}
