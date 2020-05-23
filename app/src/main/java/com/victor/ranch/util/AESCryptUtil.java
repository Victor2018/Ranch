package com.victor.ranch.util;


import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by longtv, All rights reserved.
 * -----------------------------------------------------------------
 * File: AESCryptUtil.java
 * Author: Victor
 * Date: 2018/9/6 18:02
 * Description:
 * -----------------------------------------------------------------
 */
public class AESCryptUtil {
    /** 算法/模式/填充 **/
    private static final String CipherMode = "AES/CBC/PKCS7Padding";
    public static final String KEY = "long.tv_k2na39ul";
    public static final String IV = "long.tv_aes_iviv";

    // 创建密钥, 长度为128位(16bytes), 且转成字节格式
    private static SecretKeySpec createKey(String key) {

        byte[] data = null;

        if (key == null) { key = ""; }
        StringBuffer sb = new StringBuffer(16);
        sb.append(key);
        while (sb.length() < 16) { sb.append("0"); }
        if (sb.length() > 16) { sb.setLength(16); }

        try { data = sb.toString().getBytes("UTF-8");}
        catch (UnsupportedEncodingException e) { e.printStackTrace(); }

        return new SecretKeySpec(data, "AES");
    }

    // 创建初始化向量, 长度为16bytes, 向量的作用其实就是salt
    private static IvParameterSpec createIV(String iv) {

        byte[] data = null;

        if (iv == null) { iv = ""; }
        StringBuffer sb = new StringBuffer(16);
        sb.append(iv);
        while (sb.length() < 16) { sb.append("0"); }
        if (sb.length() > 16) { sb.setLength(16); }

        try { data = sb.toString().getBytes("UTF-8"); }
        catch (UnsupportedEncodingException e) { e.printStackTrace(); }

        return new IvParameterSpec(data);
    }

    /****************************************************************************/

    // 加密字节数据, 被加密的数据需要提前转化成字节格式
    private static byte[] encrypt(byte[] content, String key, String iv) {

        try {
            SecretKeySpec secretKeySpec = createKey(key);
            IvParameterSpec ivParameterSpec = createIV(iv);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] result = cipher.doFinal(content); // 加密
            return result;
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    // 加密字符串数据, 返回的字节数据还需转化成16进制字符串
    public static String encrypt(String content, String key, String iv) {

        byte[] data = null;
        try { data = content.getBytes("UTF-8"); }
        catch (Exception e) { e.printStackTrace(); }

        data = encrypt(data, key, iv);
        String encoded = Base64.encodeToString(data, Base64.NO_WRAP);
//        return byte2hex(data);
        return encoded;
    }

    /****************************************************************************/

    // 解密字节数组
    public static byte[] decrypt(byte[] content, String key, String iv) {

        try {
            SecretKeySpec secretKeySpec = createKey(key);
            IvParameterSpec ivParameterSpec = createIV(iv);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    // 解密(输出结果为字符串), 密文为16进制的字符串
    public static String decrypt(String content, String password, String iv) {
        byte[] data = Base64.decode(content, Base64.NO_WRAP);

        data = decrypt(data, password, iv);
        if (data == null) return null;

        String result = null;
        try { result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) { e.printStackTrace(); }

        return result;
    }

    public static String base64Decrypt (String content) {
        String result = "";
        try {
            result =  new String(Base64.decode(content, Base64.NO_WRAP), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return result;
    }

    /****************************************************************************/

    // 字节数组转成16进制大写字符串
    private static String byte2hex(byte[] b) {

        String tmp = "";
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int n = 0; n < b.length; n++) {
            tmp = (Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) { sb.append("0"); }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase();
    }

    // 将16进制字符串转换成字节数组
    private static byte[] hex2byte(String inputString) {

        if (inputString == null || inputString.length() < 2) { return new byte[0]; }
        inputString = inputString.toLowerCase();
        int l = inputString.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; ++i) {
            String tmp = inputString.substring(2 * i, 2 * i + 2);
            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
        }
        return result;
    }

}
