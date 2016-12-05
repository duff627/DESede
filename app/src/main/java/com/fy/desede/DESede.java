package com.fy.desede;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 24位3Des加密
 * Created by fy on 2016/12/1.
 */
public class DESede {

    /***解密方式***/
    private static final String ENCRYPTION_MANNER = "DESede";

    /**
     * 加密
     * @param data 加密数据
     * @param key 加密密码
     * @return
     * @throws Exception
     */
    public static String encrypt3DES(byte[] data, byte[] key) throws Exception {
        // 恢复密钥
        SecretKey secretKey = new SecretKeySpec(key, ENCRYPTION_MANNER);
        // Cipher完成加密
        Cipher cipher = Cipher.getInstance(ENCRYPTION_MANNER);
        // cipher初始化
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypt = cipher.doFinal(data);
        //转码
        return new String(Base64.encode(encrypt, Base64.DEFAULT), "UTF-8");
    }

    /**
     * 解密
     * @param data 加密后的字符串
     * @param key 加密密码
     * @return
     * @throws Exception
     */
    public static String decrypt3DES(String data, byte[] key) throws Exception {
        // 恢复密钥
        SecretKey secretKey = new SecretKeySpec(key, ENCRYPTION_MANNER);
        // Cipher完成解密
        Cipher cipher = Cipher.getInstance(ENCRYPTION_MANNER);
        // 初始化cipher
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        //转码
        byte[] bytes = Base64.decode(data.getBytes("UTF-8"), Base64.DEFAULT);
        //解密
        byte[] plain = cipher.doFinal(bytes);
        //解密结果转码
        return  new String(plain, "utf-8");
    }
}

