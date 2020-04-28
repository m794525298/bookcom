package com.book.Interface;

import com.alibaba.fastjson.JSONObject;

public interface User {
	public JSONObject getUser(String id,String account);
	public int updatedUser(String id,String Icon,String nickname) ;
	public boolean validUser(String id,String account);
	public boolean saveIcon(String id,String icon);
}
