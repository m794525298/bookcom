package com.book.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Coder {
	public static final String KEY_MD5 = "BookCom";
	public static String encryptedPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(password.getBytes());
		return new String(md5.digest());
	}
	
	public static String encryptedId(int id){
		return String.valueOf(id*10078+9);
		
	}
	
	public static String decryptedId(String id) {
		return String.valueOf((Integer.valueOf(id)-9)/10078);
	}
	
	public static String textToBase64(String str, String charsetName)
            throws UnsupportedEncodingException{
        if (charsetName == null) throw new NullPointerException();
        if(str == null)
            return null;
        String res = "";
        try{
            res =  Base64.getEncoder().encodeToString(str.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return res;
    }
}
