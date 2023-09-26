package com.sertifiakt.demo;

import java.io.ByteArrayOutputStream;
import java.util.zip.Inflater;

public class Utils {
  public static byte[] decompressImage(byte[] data) {
    Inflater inflater = new Inflater();
    inflater.setInput(data);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] temp = new byte[4 * 1024];

    try {
      while (!inflater.finished()) {
        int count = inflater.inflate(temp);
        outputStream.write(temp, 0, count);
      }
      outputStream.close();
    } catch (Exception ignore) {
    }
    return outputStream.toByteArray();
  }
}
