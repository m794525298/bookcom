package com.book.Interface;

import java.sql.ResultSet;

import com.alibaba.fastjson.JSONObject;

public interface Login {
	public JSONObject login(String account, String password);
}
