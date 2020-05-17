package com.book.service;

import com.alibaba.fastjson.JSONObject;
import com.book.Interface.Regsist;
import com.book.dao.UserDao;

public class RegsistService implements Regsist{

    public RegsistService() {
    	super();
    }


	@Override
	public JSONObject regsist(String account, String username, String password, String email) {
		int code = UserDao.regsist(account,username, password, email);
		JSONObject rs = new JSONObject();
		if (code == 0) {
			rs = (new LoginService()).login(account, password);
			rs.put("success","true");
		}else {
			rs.put("success","false");
		}
		return rs;
	}

	


}
