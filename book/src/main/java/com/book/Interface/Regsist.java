package com.book.Interface;

import com.alibaba.fastjson.JSONObject;

public interface Regsist {
	public boolean sendEmailCaptcha(String email);
	public JSONObject regsist(String account,String username,String password,String email);
}
