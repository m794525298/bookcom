package com.book.service;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

import com.book.Interface.Login;
import com.book.common.Coder;
import com.book.dao.UserDao;


public class LoginService implements Login{
	
    public LoginService() {
        super();
    }

	public ResultSet login(String account, String password) {
		ResultSet rs;
		try {
			rs = UserDao.login(account, Coder.encryptedPassword(password));
			return rs;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}



}
