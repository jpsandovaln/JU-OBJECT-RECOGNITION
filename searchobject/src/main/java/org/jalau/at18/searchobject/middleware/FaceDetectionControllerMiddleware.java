package org.jalau.at18.searchobject.middleware;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import org.jalau.at18.searchobject.common.exception.MiddlewareException;
import org.jalau.at18.searchobject.common.logger.At18Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * Filters are Java classes that implement the javax.servlet.Filter interface,
 * and whose mission is to intercept requests before they reach the servlets
 * and after, to perform certain operations.
 * @author Sarai Alvarez
 * @version 1.0
 */
@WebFilter(urlPatterns = "/faceDetection")
public class FaceDetectionControllerMiddleware implements Filter {
    private static final Logger LOG = new At18Logger().getLogger();
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
                    throw new MiddlewareException("    The type field is empty ");
                }
            }  else {
                    LOG.warning(" ENTER AN IMAGE ");
                    throw new MiddlewareException("    Enter a image ");
                }
        } catch (InstantiationError | MiddlewareException e) {
                LOG.warning(" ERROR LOADING THE MODEL " + e);
                e.printStackTrace();
                PrintWriter out = response.getWriter();
                res.setStatus(400);
                out.println ("    Status :    " + res.getStatus());
                out.println (e.getMessage());
        }
    }
}
