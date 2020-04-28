package com.book.service;

import java.security.NoSuchAlgorithmException;

import com.book.Interface.Regsist;
import com.book.common.Coder;
import com.book.dao.UserDao;

public class RegsistService implements Regsist{

    public RegsistService() {
    	
    }




	public int regsist(String account, String password,String email) {
		int code;
		try {
			code = UserDao.regsist(account, Coder.encryptedPassword(password), email);
			return code;
		} catch (NoSuchAlgorithmException e) {
			return 1;
		}
		
	}

	


}
