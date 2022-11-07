package org.jalau.at18.searchobject.middleware;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import com.google.gson.Gson;
import org.jalau.at18.searchobject.common.exception.MiddlewareException;
import org.jalau.at18.searchobject.common.logger.At18Logger;
import org.jalau.at18.searchobject.controller.response.ErrorResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Logger;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.Filter;

/**
 * Filters are Java classes that implement the javax.servlet.Filter interface,
 * and whose mission is to intercept requests before they reach the servlets
 * and after, to perform certain operations.
 * @author Sarai Alvarez
 * @version 1.0
 */
@WebFilter(urlPatterns = "/emotionRecognition")

public class EmotionRecognitionMiddleware implements Filter {
    private static final Logger LOG = new At18Logger().getLogger();
    private Gson gson = new Gson();
    private static final int FILE_SIZE = 161;
    /**
     * doFilter: It is the one that contains the logic of what the filter does. It receives by parameter the request, the response and the chain of filters.
     * Servlets: which contain the logic that is applied when receiving an HTTP request.
     * Filters: which are applied to HTTP requests before or after they have been served by the servlets.
     * @param //request the user request data (all incoming information).
     * @param  // response the servlet response to the request
     */
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = System.getProperty("user.dir") + "\\src\\main\\java\\org\\jalau\\at18\\searchobject\\middleware\\token\\token.txt";
        File fileToken = new File(path);
        Scanner myReader = new Scanner(fileToken);
        String token = myReader.nextLine(); //Read the txt file
        int tokenCounter = Integer.parseInt(myReader.nextLine()); //Read the txt file

        try {
            LOG.info("MODEL EMOTION RECOGNITION");

            //Verity if have a valid token
            if (req.getHeader("Authorization").contains(token) && tokenCounter >= 1) {
                FileWriter fw = new FileWriter(path, false);
                tokenCounter -= 1; //If it reaches 0 the file is cleaned
                LOG.info("Remaining token uses: " + String.valueOf(tokenCounter));
                fw.write(token);
                fw.write(System.getProperty("line.separator"));
                fw.write(Integer.toString(tokenCounter));
                fw.close();
                myReader.close();

                //Verify that an empty or null file isn't entered
                if (req.getPart("file").getSize() != 0L && req.getPart("file").getSize() > FILE_SIZE && req.getPart("file").getContentType() != null) {
                    LOG.info(" ACCEPT THE FILE ");
                    if(req.getPart("file").getContentType().contains("jpg") || req.getPart("file").getContentType().contains("png") || req.getPart("file").getContentType().contains("jpeg")){

                        LOG.info("VALIDATED THE IMAGE ");
                        //Verify that a field isn't empty
                        if (!req.getParameter("token").isEmpty()) {
                            LOG.info("EMOTION RECOGNITION ---- RUNNING -------");
                            chain.doFilter(request, response);
                        } else {
                            LOG.warning(" ENTER AN TOKEN ");
                            throw new MiddlewareException(" Enter an token ");
                        }
                    } else {
                        LOG.warning(" ENTER AN IMAGE ");
                        throw new MiddlewareException(" Enter a image jpg, png or jpeg");
                    }

                } else {
                        LOG.warning(" ENTER AN IMAGE ");
                    throw new MiddlewareException(" Enter a image jpg, png or jpeg");
                }
            }
            else if (tokenCounter < 1) {
                LOG.info("TOKEN HAS NO MORE USES, PLEASE REQUEST ANOTHER ONE");
                FileWriter fw = new FileWriter(path, false);
                PrintWriter pw = new PrintWriter(fw, false);
                pw.flush(); //delete the content of the txt file.
                pw.close();
                fw.close();
                throw new MiddlewareException(" Token has no more uses, please request another one");
            } else {
                LOG.warning(" GENERATED TOKEN");
                throw new MiddlewareException(" Generated token ");
            }
        } catch (InstantiationError | MiddlewareException e) {
            LOG.warning(" ERROR LOADING THE MODEL " + e);
            e.printStackTrace();
            res.setStatus(400);
            String status = Integer.toString( res.getStatus());
            ErrorResponse errorResponse = new ErrorResponse(status, e.getMessage());
            String errorResponseJsonString = this.gson.toJson(errorResponse);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(errorResponseJsonString);
            out.flush();
        }
    }
}
