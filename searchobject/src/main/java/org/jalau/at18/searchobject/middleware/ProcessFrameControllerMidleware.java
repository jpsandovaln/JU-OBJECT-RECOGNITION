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
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
/**
 * doFilter: It is the one that contains the logic of what the filter does. It receives by parameter the request, the response and the chain of filters.
 * Servlets: which contain the logic that is applied when receiving an HTTP request.
 * Filters: which are applied to HTTP requests before or after they have been served by the servlets.
 * @param // request it's the image that user will upload to analyze
 * @param // response the type of face that we want to detect

 */
@WebFilter(urlPatterns = "/processFrame")
public class ProcessFrameControllerMidleware implements Filter {
    private static final Logger LOG = new At18Logger().getLogger();
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try{
            //Verify that an empty or null file isn't entered
            if(req.getPart("file").getSize() != 0L && req.getPart("file").getSize() > 100 && req.getPart("file").getContentType() != null  &&  req.getPart("file").getContentType().contains("zip")) {
                System.out.println("MODEL PROCESS FRAME CONTROLLER");
                //Verify that a field isn't empty
                if(!req.getParameter("searchCriteria").isEmpty() && !req.getParameter("occurrencyPercentage").isEmpty() && !req.getParameter("modelObjectRecognizer").isEmpty() && !req.getParameter("notifierType").isEmpty()) {
                    System.out.println("PROCESS FRAME CONTROLLER ------ RUNNING ------");
                    chain.doFilter(request, response);
                } else {
                    LOG.info(" THE FIELDS ARE EMPTY ");
                }
            }  else {
                LOG.info(" THE FILE IS EMPTY OR NOT A .zip FILE ");
            }

        } catch (InstantiationError e) {
            LOG.info(" ERROR LOADING THE MODEL "+e);
            e.printStackTrace();
        }
    }
}

