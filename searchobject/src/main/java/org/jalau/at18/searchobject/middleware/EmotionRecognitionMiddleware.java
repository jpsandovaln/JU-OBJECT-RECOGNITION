package org.jalau.at18.searchobject.middleware;
import org.jalau.at18.searchobject.common.logger.At18Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/emotionRecognition")
public class EmotionRecognitionMiddleware implements Filter {
    private static final Logger LOG = new At18Logger().getLogger();
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try{
            LOG.info("MODEL EMOTION RECOGNITION");
            if(req.getPart("file").getSize() != 0L && req.getPart("file").getSize() > 100 && req.getPart("file").getContentType() != null) {
                LOG.info(" ACCEPT THE IMAGE ");
                if(!req.getParameter("token").isEmpty()) {
                    System.out.println("EMOTION RECOGNITION ---- RUNNING -------");
                    chain.doFilter(request, response);
                } else {
                    LOG.warning(" ENTER AN TOKEN ");
                }
            }  else {
                LOG.warning(" ENTER AN IMAGE ");
            }


        } catch (InstantiationError e) {
            LOG.info(" ERROR LOADING THE MODEL "+e);
            e.printStackTrace();
        }

    }
}
