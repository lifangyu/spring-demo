/**  
 * @(#)MD5.java	  2016年7月9日 下午10:17:58
 *
 * Copyright 2016 Lify, Inc. All rights reserved.
 * Lify PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package com.spring.common.utils.arithmetic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密算法
 * @author lifangyu
 * @version V1.0
 */
public class MD5 {

    // 加的盐
    private static final String SALT = "HXWcjvQWVG1wI4FQBLZpQ3pWj48AV63d";

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

    public static void main(String args[]) {
        System.out.println(MD5.encodeByMd5AndSalt("123456"));
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

    public static String byteHEX(byte ib) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

}
