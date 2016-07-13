/**  
 * @(#)Digests.java   2016年7月9日 下午10:17:58
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.spring.common.utils.arithmetic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SHA1/MD5 加密算法
 * @author lifangyu
 * @version V1.0
 */
public class Digests {

    private static Logger logger = LoggerFactory.getLogger(Digests.class);


    private static final String SHA1 = "SHA-1";

    private static final String MD5 = "MD5";

    /**
     * 经过多少次运算
     */
    private static final int INTERATIONS = 1024;

    /**
     * 加盐的长度
     */
    private static final int SALT_SIZE = 8;

    private static SecureRandom random = new SecureRandom();


    public static byte[] sha1(byte[] input) {
        return digest(input, SHA1, null, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt) {
        return digest(input, SHA1, salt, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return digest(input, SHA1, salt, iterations);
    }

    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; ++i) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            logger.error("Digests.digest Exception", e);
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] md5(InputStream input) throws IOException {
        return digest(input, MD5);
    }

    public static byte[] sha1(InputStream input) throws IOException {
        return digest(input, SHA1);
    }

    private static byte[] digest(InputStream input, String algorithm) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8192;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer, 0, bufferLength);

            while (read > -1) {
                messageDigest.update(buffer, 0, read);
                read = input.read(buffer, 0, bufferLength);
            }
            return messageDigest.digest();
        } catch (GeneralSecurityException e) {
            logger.error("Digests.digest Exception", e);
            e.printStackTrace();
        }
        return null;
    }
    
    public static class HashPassword {
        public String salt;
        public String password;
    }

    /**
     * 
     * SHA1生成帶有盐的密码
     *
     * @author lifangyu
     * @param password
     * @return
     * 		HashPassword
     */
    public static HashPassword encrypt(String password) {
        HashPassword result = new HashPassword();
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        result.salt = DigestsEncodes.encodeHex(salt);
        byte[] hashPassword = Digests.sha1(password.getBytes(), salt, INTERATIONS);

        result.password = DigestsEncodes.encodeHex(hashPassword);
        return result;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        HashPassword passSHA1 = Digests.encrypt("lfy2016YF");
        System.out.println(passSHA1.salt + ";" + passSHA1.password);
        System.out.println(Digests.md5(new ByteArrayInputStream("lfy2016YF".getBytes("UTF-8"))));
    }
}