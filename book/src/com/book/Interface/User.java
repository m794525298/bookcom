package com.book.Interface;

import com.alibaba.fastjson.JSONObject;

public interface User {
	public JSONObject getUser(String id);
	public boolean updatedUser(String id,String nickname) ;
	public boolean validUser(String id);
	public String updatedIcon(String id,String Icon);
	public boolean updatedUserPassword(String id,String oldPassword,String newPassword);
}
