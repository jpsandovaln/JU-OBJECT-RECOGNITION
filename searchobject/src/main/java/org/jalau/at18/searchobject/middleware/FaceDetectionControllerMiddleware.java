package org.jalau.at18.searchobject.middleware;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import org.jalau.at18.searchobject.common.logger.At18Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
/**
 * doFilter: It is the one that contains the logic of what the filter does. It receives by parameter the request, the response and the chain of filters.
 * Servlets: which contain the logic that is applied when receiving an HTTP request.
 * Filters: which are applied to HTTP requests before or after they have been served by the servlets.
 * @param // request it's the image that user will upload to analyze
 * @param // response the type of face that we want to detect

 */
@WebFilter(urlPatterns = "/faceDetection")
public class FaceDetectionControllerMiddleware implements Filter {
    private static final Logger LOG = new At18Logger().getLogger();
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try{
            LOG.info(" MODEL FACE DETECTION  ");
            //Verify that an empty or null file isn't entered
            if(req.getPart("file").getSize() != 0L && req.getPart("file").getSize() > 100 && req.getPart("file").getContentType() != null) {
                LOG.info(" ACCEPT THE IMAGE ");
                //Verify that a field isn't empty
                if(!req.getParameter("type").isEmpty()) {
                    LOG.info("FACE DETECTION ---- RUNNING -------");
                    chain.doFilter(request, response);
                } else {
                    LOG.warning(" THE TYPE FIELD IS EMPTY ");
                }
            } else {
                LOG.warning(" ENTER AN IMAGE ");
            }
        } catch (InstantiationError e) {
            LOG.info(" ERROR LOADING THE MODEL "+e);
            e.printStackTrace();
        }

    }
}
