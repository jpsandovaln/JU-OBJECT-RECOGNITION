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

        System.out.println(" ENTRO A EMOTION RECOGNITION" );

        try{
            LOG.info("Esta entrando emotion     Sergio");
            if(req.getPart("file").getSize() != 0L && req.getPart("file").getSize() > 100 && req.getPart("file").getContentType() != null) {
                System.out.println("ACEPTA EL ARCHIVO ");
                if(!req.getParameter("token").isEmpty()) {
                    System.out.println("PROCES FRAME CONTROLLER ---- EJECUTANDO -------");
                    chain.doFilter(request, response);
                } else {
                    LOG.info("El archivo esta vacio o no es un archivo .zip");
                }
            }  else {
                LOG.info("El archivo esta vacio o no es un archivo .zip");
            }


        } catch (InstantiationError e) {
            LOG.info("El archivo esta vacio o no es un archivo .zip" + e);
            e.printStackTrace();
        }

    }
}
