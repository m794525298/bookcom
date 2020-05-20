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
				res.put("UserID", rs.getObject("USER_MD5ID"));
				res.put("nickname",rs.getObject("USER_NICKNAME"));
				res.put("icon",rs.getObject("USER_ICON"));
				res.put("followersNum",rs.getObject("USER_FOLLOWERSNUM"));
				res.put("followingNum",rs.getObject("USER_FOLLOWINGNUM"));
			}
			return res;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}



}
