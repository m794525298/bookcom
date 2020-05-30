package com.book.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.book.Interface.User;
import com.book.common.Coder;
import com.book.dao.CommentDao;
import com.book.dao.FollowDao;
import com.book.dao.PostDao;
import com.book.dao.UserDao;
import com.book.pojo.CommentBean;
import com.book.pojo.UserBean;

public class UserService implements User{
	
	public UserService(){
		super();
	}
	
	

	public JSONObject getUser(String id) {
		JSONObject res = new JSONObject();
		ResultSet rs = UserDao.getUser(id);
		try {
			if (!rs.next()) return null;
			rs.previous();
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
		String path = System.getProperty("user.home")+"/book/src/main/webapp/Icon/" + id +".png";
		return	Coder.saveBase64Image(path, icon);
	}

	@Override
	public String updatedIcon(String id, String icon) {
		saveIcon(id,icon);
		if (UserDao.updatedUserIcon(id, "Icon/" + id +".png") == 0){
			return "Icon/" + id +".png";
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



	@Override
	public boolean validEmail(String email) {
		return (UserDao.checkEmail(email) == 0)?true:false;
	}



	@Override
	public boolean updatedUserPasswordByEmail(String email, String newPassword) {
		if (UserDao.updatedUserPasswordByEmail(email, newPassword) == 0) {
			return true;
		}
		return false;
	}



	@Override
	public JSONObject getUserName(String id) {
		JSONObject jo = new JSONObject();
		ResultSet rs = UserDao.getUserName(id);
		try {
			if (!rs.next()) return null;
			rs.previous();
			while(rs.next()) {
				jo.put("username",rs.getString("USER_NICKNAME"));
			}
			return jo;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public JSONObject getFollow(String id) {
		JSONObject json = new JSONObject();
		ResultSet rs = UserDao.getFollow(id);
		try {
			if(rs.next()) {
				json.put("followerNum", rs.getString("USER_FOLLOWERNUM"));
				json.put("followingNUM", rs.getString("USER_FOLLOWINGNUM"));
			} else {
				json.put("followerNum", "0");
				json.put("followingNum", "0");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public JSONObject getHotUser() {
		ResultSet rs = UserDao.getHotUser();
		JSONObject jo = new JSONObject();
		try {
			rs.last();
			jo.put("num", rs.getRow());
			rs.beforeFirst();
			List<JSONObject> list = new LinkedList<JSONObject>();
			while(rs.next()) {
				JSONObject temp = new JSONObject();
				temp.put("userID", rs.getString("USER_MD5ID"));
				temp.put("icon" , rs.getString("USER_ICON"));
				temp.put("username", rs.getString("USER_NICKNAME"));
				temp.put("followersNum", rs.getString("USER_FOLLOWERSNUM"));
				list.add(temp);
			}
			jo.put("list",list);
			return jo;
		} catch (SQLException e) {
			return null;
		}
	}



	@Override
	public boolean validAccount(String account) {
		return (UserDao.checkAccount(account)==0)?true:false;
	}



	@Override
	public JSONObject getOtherUser(String id) {
		JSONObject res = new JSONObject();
		ResultSet rs = UserDao.getUser(id);
		
		try {
			if (!rs.next()) return null;
			rs.previous();
			while(rs.next()) {
			res.put("userID", rs.getString("USER_MD5ID"));
			res.put("icon", rs.getString("USER_ICON"));
			res.put("username", rs.getString("USER_NICKNAME"));
			}
			return res;
		} catch (SQLException e) {
			return null;
		}
	}



	@Override
	public JSONObject getFollowNum(String userId) {
		JSONObject json = new JSONObject();
		ResultSet rs = UserDao.getUser(userId);
		try {
			if(rs.next()) {
				json.put("followersNum", rs.getString("USER_FOLLOWERSNUM"));
				json.put("followingNum", rs.getString("USER_FOLLOWINGNUM"));
			} else {
				json.put("followersNum", "0");
				json.put("followingNum", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}
}
