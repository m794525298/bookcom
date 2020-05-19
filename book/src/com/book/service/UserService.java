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
	public boolean updatedUser(String id, String nickname) {
		int code = UserDao.updatedUserData(id,nickname);
		return (code == 0)?true:false;
	}



	@Override
	public boolean validUser(String id) {
		return UserDao.validUser(id);
	}


	private boolean saveIcon(String id , String icon) {
		String path = "/Icon/" + id +".jpg";
		return	Coder.saveBase64Image(path, icon);
	}



	@Override
	public String updatedIcon(String id, String icon) {
		saveIcon(id,icon);
		if (UserDao.updatedUserIcon(id,"/Icon/" + id +".jpg") == 0){
			return "/Icon/" + id +".jpg";
		}else {
			return null;
		}
		
	}



	@Override
	public boolean updatedUserPassword(String id, String oldPassword, String newPassword) {
		if (UserDao.updatedUserPassword(id, oldPassword, newPassword) == 0) {
			return true;
		}else {
			return false;
		}
		
	}


}
