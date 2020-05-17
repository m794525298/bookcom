package com.book.common;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import sun.misc.BASE64Decoder;

public class Coder {
	public static final String KEY_MD5 = "BookCom";
	public static String encrypted(String s){
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance(KEY_MD5);
			md5.update(s.getBytes());
			return new String(md5.digest());
		} catch (NoSuchAlgorithmException e) {
			return s;
		}
        
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
	
	public static boolean saveBase64Image(String Path,String imgStr) {
		if (imgStr == null) //图像数据为空  
            return false;
		BASE64Decoder decoder = new BASE64Decoder();  
		try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            
            OutputStream out = new FileOutputStream(Path);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
		
	}
}
