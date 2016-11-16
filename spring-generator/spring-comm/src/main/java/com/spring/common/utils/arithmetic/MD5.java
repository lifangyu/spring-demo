/**  
 * @(#)MD5.java	  2016年7月9日 下午10:17:58
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.spring.common.utils.arithmetic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5 加密算法
 * @author lifangyu
 * @version V1.0
 */
public class MD5 {

    private static Logger logger = LoggerFactory.getLogger(MD5.class);

    // 加的盐
    private static final String SALT = "HXWcjvQWVG1wI4FQBLZpQ3pWj48AV63d";

    /**
     * 加盐的长度
     */
    private static final int SALT_SIZE = 8;

    private static SecureRandom random = new SecureRandom();

    public static String EncoderByMd5(String buf) {
        try {
            MessageDigest digist = MessageDigest.getInstance("MD5");
            byte[] rs = digist.digest(buf.getBytes());
            StringBuffer digestHexStr = new StringBuffer();
            for (int i = 0; i < 16; i++) {
                digestHexStr.append(byteHEX(rs[i]));
            }
            return digestHexStr.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static class HashPassword {
        public String salt;
        public String password;

        public String toString() {
            return "salt:" + salt + ";password:" + password;
        }
    }

    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }

    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            logger.error("DigestsEncodes.decodeHex Exception", e);
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 
     * MD5生成帶有盐的密码
     *
     * @author lifangyu
     * @param password
     * @return
     *      HashPassword
     */
    public static HashPassword encrypt(String password) {
        HashPassword result = new HashPassword();
        byte[] salt = generateSalt(SALT_SIZE);
        result.salt = encodeHex(salt);
        result.password = encodeByMd5AndAutoSalt(password, result.salt);
        return result;
    }

    /**
     *生成一定长度的随机盐 
     *
     * @author lifangyu
     * @param numBytes
     * @return
     * 		byte[]
     */
    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 加盐的md5值。这样即使被拖库，仍然可以有效抵御彩虹表攻击
     * 
     * @param inbuf
     *            需做md5的字符串
     * @return
     * 
     */
    public static String encodeByMd5AndSalt(String inbuf) {
        return EncoderByMd5(EncoderByMd5(inbuf) + SALT);
    }

    /**
     * 
     * 随机添加盐生成密码
     * @author lifangyu
     * @param inbuf
     * @param salt
     * @return
     * 		String
     */
    public static String encodeByMd5AndAutoSalt(String inbuf, String salt) {
        return EncoderByMd5(EncoderByMd5(inbuf) + salt);
    }

    public static String byteHEX(byte ib) {
        // char[] Digit ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    public static void main(String args[]) {
        HashPassword hs = MD5.encrypt("123456");

        System.out.println(hs.toString());

        System.out.println(MD5.encodeHex(MD5.decodeHex(hs.salt)));

        System.out.println(MD5.encodeByMd5AndAutoSalt("123456", hs.salt));


    }

}
