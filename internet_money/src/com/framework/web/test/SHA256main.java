package com.framework.web.test;

import com.framework.core.utils.RC4;
import com.framework.core.utils.SHA256;

public class SHA256main {
	public static final String SHA256KEY = "TyCf" + "#" + "jnKCT" + "@" + "2015" + "&" + "SHA256KEY";
    public static final String RC4KEY = "Tkjf" + "#" + "jnkct" + "&" + "2015" + "!" + "RC4KEY";


	public static void main(String[] args) {
		System.out.println(SHA256.getSHA256CheckSum("123456", SHA256KEY));
        System.out.println(RC4.encry_RC4_string("password123!dadf", RC4KEY));
        System.out.println(RC4.decry_RC4_string("2365ec57806b02bd9955f4d5c1593ef6", RC4KEY));
	}
	
}
