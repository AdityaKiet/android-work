package com.o9pathshala.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

	 public final static String encryptSHA512(String target) {
	        try {
	            MessageDigest sh = MessageDigest.getInstance("SHA-512");
	            sh.update(target.getBytes());
	            StringBuffer sb = new StringBuffer();
	            for (byte b : sh.digest()) sb.append(Integer.toHexString(0xff & b));
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }
}
