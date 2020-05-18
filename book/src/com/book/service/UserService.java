package com.book.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.alibaba.fastjson.JSONObject;
import com.book.Interface.User;
import com.book.common.Coder;
import com.book.dao.UserDao;
import com.book.pojo.UserBean;

public class UserService implements User{
	
	public UserService(){
		super();
	}
	
	

	public JSONObject getUser(String id) {
		JSONObject res = new JSONObject();
		ResultSet rs = UserDao.getUser(id);
		try {
			if (rs.wasNull()) return null;
			ResultSetMetaData md;
			md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			for (int i = 4; i <= columnCount; i++) {
				res.put(md.getColumnName(i), rs.getObject(i));//获取键名及值
			}
			return res;
		} catch (SQLException e) {
			return null;
		}
	}



	@Override
	public int updatedUser(String id, String icon, String nickname) {
		UserBean user = new UserBean();
		user.setId(id);
		user.setIcon(icon);
		user.setNickname(nickname);
		int code = UserDao.updatedUserData(user);
		return code;
	}



	@Override
	public boolean validUser(String id) {
		return UserDao.validUser(id);
	}



	@Override
	public boolean saveIcon(String id , String icon) {
		String path = "/Icon/" + id +".jpg";
		return	Coder.saveBase64Image(path, icon);
	}


}
