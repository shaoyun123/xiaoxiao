package com.web.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesUtil {
	 public static void main(String[] args) throws UnsupportedEncodingException {  
        String content = "10010_2014014281";  
        String key = "xiaoxiao";  
  
  
        System.out.println("加密前：" + content);  
        byte[] encrypted = DES_CBC_Encrypt(content.getBytes(), key.getBytes());  
          
        System.out.println("加密后：" + byteToHexString(encrypted));  
          
        //对String进行解密  
        byte[] encrypted1 = hexStringToByteArray(byteToHexString(encrypted));  
        byte[] decrypted1 = DES_CBC_Decrypt(encrypted1, key.getBytes());  
        System.out.println("解密String：" + new String(decrypted1));  
        String dd1=new String(decrypted1);  
        //对byte进行解密  
        byte[] decrypted = DES_CBC_Decrypt(encrypted, key.getBytes());  
        System.out.println("解密byte：" + new String(decrypted));  
        String dd=new String(decrypted);  
        if(dd1.endsWith(dd)){  
        System.out.println("byte与String匹配成功");  
        }  
    }  
	  
	  
    public static byte[] DES_CBC_Encrypt(byte[] content, byte[] keyBytes) {  
        try {  
            DESKeySpec keySpec = new DESKeySpec(keyBytes);  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
            SecretKey key = keyFactory.generateSecret(keySpec);  
  
  
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");  
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keySpec.getKey()));  
            byte[] result = cipher.doFinal(content);  
            return result;  
        } catch (Exception e) {  
            System.out.println("exception:" + e.toString());  
        }  
        return null;  
    }  
	  
	  
    private static byte[] DES_CBC_Decrypt(byte[] content, byte[] keyBytes) {  
        try {  
            DESKeySpec keySpec = new DESKeySpec(keyBytes);  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
            SecretKey key = keyFactory.generateSecret(keySpec);  
  
  
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");  
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(keyBytes));  
            byte[] result = cipher.doFinal(content);  
            return result;  
        } catch (Exception e) {  
            System.out.println("exception:" + e.toString());  
        }  
        return null;  
    }  
    public static byte[] hexStringToByteArray(String s) {  
        int len = s.length();  
        byte[] data = new byte[len / 2];  
        for (int i = 0; i < len; i += 2) {  
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)  
                                 + Character.digit(s.charAt(i+1), 16));  
        }  
        return data;  
    }  
    public static String byteToHexString(byte[] bytes) {  
        StringBuffer sb = new StringBuffer(bytes.length);  
        String sTemp;  
        for (int i = 0; i < bytes.length; i++) {  
            sTemp = Integer.toHexString(0xFF & bytes[i]);  
            if (sTemp.length() < 2)  
                sb.append(0);  
            sb.append(sTemp);  
        }  
        return sb.toString();  
    }  

}
