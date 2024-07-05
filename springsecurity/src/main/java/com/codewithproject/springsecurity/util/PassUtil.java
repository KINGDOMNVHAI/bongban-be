package com.codewithproject.springsecurity.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassUtil {

    public static String generateMD5(String input) {
        try {
            // Create instance of MD5 message digest
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Convert the input string to bytes
            byte[] inputBytes = input.getBytes();

            // Generate the MD5 hash
            byte[] hashBytes = md.digest(inputBytes);

            // Convert the hash bytes to hexadecimal representation
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
