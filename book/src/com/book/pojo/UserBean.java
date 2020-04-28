package com.book.pojo;

import com.book.common.Coder;

public class UserBean {
	private String id;
	private String icon;
	private String nickname;
	public UserBean(){
		super();
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getId() {
		return id;
	}
	
	public String getIcon() {
		return  icon;
	}

	public String getNickname() {
		return nickname;
	}
	

}
