package com.framework.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class SHA256 {
	
	public static final String SHA256KEY = "TyCf" + "#" + "jnKCT" + "@" + "2015" + "&" + "SHA256KEY";
    public static final String RC4KEY = "Tkjf" + "#" + "jnkct" + "&" + "2015" + "!" + "RC4KEY";
	
	public static String getSHA256CheckSum(String data, String key) {
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] keyBytes = new byte[0];
        try {
            keyBytes = key.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] dataBytes = new byte[0];
        try {
            dataBytes = data.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        try {
            mac.init(secretKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] doFinal = mac.doFinal(dataBytes);
        byte[] hexBytes = new Hex().encode(doFinal);
        return new String(hexBytes);
    }

}
