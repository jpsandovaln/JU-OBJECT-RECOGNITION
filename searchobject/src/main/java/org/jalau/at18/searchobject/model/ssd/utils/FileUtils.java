package org.jalau.at18.searchobject.model.ssd.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class FileUtils {
   private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

   public static InputStream getResource(String filename) throws IOException {
      ClassLoader classLoader = FileUtils.class.getClassLoader();
      URL dataFile = classLoader.getResource(filename);
      return Objects.requireNonNull(dataFile).openStream();
   }
   public static InputStream getResourceStream(String filename)  {
      ClassLoader classLoader = FileUtils.class.getClassLoader();
      return classLoader.getResourceAsStream(filename);
   }
   public static byte[] getBytes(String filename) {
      InputStream inputStream = getResourceStream(filename);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int length;
      try {
         while((length = inputStream.read(buffer)) > 0){
            baos.write(buffer, 0, length);
         }
      }
      catch (IOException e) {
         logger.error("Failed to get bytes from " + filename, e);
      }
      return baos.toByteArray();
   }
}
