package com.book.Interface;

import com.alibaba.fastjson.JSONObject;

public interface User {
	public JSONObject getUser(String id);
	public int updatedUser(String id,String Icon,String nickname) ;
	public boolean saveIcon(String id,String icon);
	boolean validUser(String id);
}
