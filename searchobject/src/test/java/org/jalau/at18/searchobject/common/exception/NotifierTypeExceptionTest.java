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

public class NotifierTypeExceptionTest {

    @Test
    public void shouldBuildNotifierTypeException() {
        NotifierTypeException notifierType = new NotifierTypeException("The notifier exception");
        assertEquals(NotifierTypeException.class, notifierType.getClass());
    }

    @Test
    public void shouldBuildNotifierTypeExceptionWithThrowable() {
        NotifierTypeException notifierType = new NotifierTypeException("The notifier exception", new Throwable());
        assertEquals(NotifierTypeException.class, notifierType.getClass());
    }
}