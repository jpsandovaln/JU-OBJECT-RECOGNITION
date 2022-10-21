/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package org.jalau.at18.searchobject.common.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.LogManager;

/**
 * It is responsible for initialice the Logger and associate it with the properties file.
 *
 * @author Adriana Olivera Ordoñez
 * @version 1.0
 */

public class At18Logger {

    // Directory of the properties file
    private static final String PROPERTIES_PATH = "src\\main\\resources\\application.properties";
    private static Logger log;
    private static At18Logger at18logger;

    private At18Logger () {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream(PROPERTIES_PATH));

        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
    }
    public static Logger getLogger() {
        if (log == null) {
            at18logger = new At18Logger();
            log = Logger.getLogger("At18Logger");
        }
        return log;
    }
}
