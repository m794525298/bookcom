package com.book.service;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import com.alibaba.fastjson.JSONObject;
import com.book.Interface.Regsist;
import com.book.common.EmailSender;
import com.book.dao.UserDao;

public class RegsistService implements Regsist{

    public RegsistService() {
    	super();
    }

    @Override
    public boolean sendEmailCaptcha(String email) {
    	// TODO Auto-generated method stub
    	Random rand = new Random();
		String captcha = String.valueOf(rand.nextInt(900000) + 100000);
		
    	int res = UserDao.saveEmailCaptcha(email, captcha);
    	if(res == 0) {
			try {
				EmailSender.send(email, captcha);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
    	} else {
			return false;
		}
    	
    	return true;
    }
    
    public boolean isCaptchaCorrect(String email, String captcha) {
    	// TODO Auto-generated method stub
    	Random rand = new Random();
		
    	Vector<String> res = UserDao.getCaptcha(email);
    	
    	if(res != null) {
    		int size = res.size();
			for(int i = 0; i < size; ++i)
				if(captcha.equals(res.get(i)))
					return true;
    	}
    	
    	return false;
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
