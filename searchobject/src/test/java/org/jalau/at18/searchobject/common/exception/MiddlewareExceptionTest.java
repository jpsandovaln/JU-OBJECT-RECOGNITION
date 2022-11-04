/*
  Copyright (c) 2022 Jala University.
  This software is the confidential and property information of Jalasoft
  ("Confidential Information"). You shall not disclose such Confidential
  Information and shall use it only in accordance with the terms of the
  Licence agreement you entered into with Jalasoft
*/
package org.jalau.at18.searchobject.common.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MiddlewareExceptionTest {

    @Test
    public void shouldBuildMiddlewareException() {
        MiddlewareException middlewareException = new MiddlewareException("The middleware exception");
        assertEquals(MiddlewareException.class, middlewareException.getClass());
    }

    @Test
    public void shouldBuildMiddlewareExceptionWithThrowable() {
        MiddlewareException middlewareException = new MiddlewareException("The middleware exception", new Throwable());
        assertEquals(MiddlewareException.class, middlewareException.getClass());
    }
}