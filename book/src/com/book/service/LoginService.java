package com.book.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.fastjson.JSONObject;
import com.book.Interface.Login;
import com.book.common.Coder;
import com.book.dao.UserDao;


public class LoginService implements Login{
	
    public LoginService() {
        super();
    }

	public JSONObject login(String account, String password) {
		ResultSet rs;
		try {
			rs = UserDao.login(account, password);
			JSONObject res = new JSONObject();
			if (!rs.next()) return null;
			rs.previous();
			while (rs.next()) {
				res.put("userID", rs.getString("USER_MD5ID"));
				res.put("account", rs.getString("USER_ACCOUNT"));
				res.put("email", rs.getString("USER_EMAIL"));
				res.put("username",rs.getString("USER_NICKNAME"));
				res.put("identity",rs.getString("USER_IDENTITY"));
				res.put("icon",rs.getString("USER_ICON"));
				res.put("followersNum",rs.getString("USER_FOLLOWERSNUM"));
				res.put("followingNum",rs.getString("USER_FOLLOWINGNUM"));
			}
			return res;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}



}
