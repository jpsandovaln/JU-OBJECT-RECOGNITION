package org.jalau.at18.searchobject.middleware;
/**
 * Copyright (c) 2022 Jala University.
 *
 * This software is the confidential and property information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * It is responsible to test the EmotionRecognitionMiddleware Class, as it is being used Filters, it is necessary to mock the funcionality
 * of request, response and filterchain, Spring offers the "Mocks" of these three classes
 * @author Sarai Alvarez
 * @version 1.0
 */
public class EmotionRecognitionMiddlewareTest {
    /**
     * This Test Case checks if the middleware and controller works correctly inlcuding the token
     */
    @Test
    public void testPositiveEmotionRecognitionMiddlewareTest() throws IOException, ServletException {
        EmotionRecognitionMiddleware emotionRecognitionMiddleware = new EmotionRecognitionMiddleware();
        String fileName = "profile.jpg";

        File newFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\org\\jalau\\at18\\searchobject\\middleware\\faceDetection\\profile.jpg");
        byte[] bFile = new byte[(int) newFile.length()];

        // does not verify the content of the .zip file
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", fileName, "image/jpg", bFile);
        MockMultipartHttpServletRequest req = new MockMultipartHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockFilterChain mockChain = new MockFilterChain();
        req.setMethod("POST");
        req.setContentType("multipart/form-data; boundary=<calculated when request is sent>");
        req.addHeader("Authorization", "Bearer 1667509793070-23cec5f3-8432-4f60-b28c-256b6231c627"); //add the current token which is in the file token.txt
        req.addFile(mockMultipartFile); // search addPart funtionalities and version of spring.
        req.addParameter("token", "667509793070-23cec5f3KFHT84324f60VMb28c256b6231c627");
        emotionRecognitionMiddleware.doFilter(req, res, mockChain);

        assertEquals(req.getParameter("token"), "667509793070-23cec5f3KFHT84324f60VMb28c256b6231c627");
        assertThat(res.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * This Test Case checks if the middleware and controller works correctly with a empty field on parameters sended
     */

    @Test
    public void testNegativeEmotionRecognitionMiddlewareTest() throws IOException, ServletException, NullPointerException{
        EmotionRecognitionMiddleware emotionRecognitionMiddleware = new EmotionRecognitionMiddleware();
        String fileName = "profile.jpg";

        File newFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\org\\jalau\\at18\\searchobject\\middleware\\faceDetection\\profile.jpg");
        byte[] bFile = new byte[(int) newFile.length()];

        // does not verify the content of the .zip file
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", fileName, "image/jpg", bFile);
        MockMultipartHttpServletRequest req = new MockMultipartHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        MockFilterChain mockChain = new MockFilterChain();
        req.setMethod("POST");
        req.setContentType("multipart/form-data; boundary=<calculated when request is sent>");
        req.addHeader("Authorization", "Bearer 1667509793070-23cec5f3-8432-4f60-b28c-256b6231c627"); //add the current token which is in the file token.txt
        req.addFile(mockMultipartFile); // search addPart funtionalities and version of spring.
        req.addParameter("token", "");
        emotionRecognitionMiddleware.doFilter(req, res, mockChain);

        assertEquals(req.getParameter("token"), "");
        assertThat(res.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}