package com.book.pojo;

public class UserBean {
	private String id;
	private String nickname;
	private String account;
	private String password;
	private String icon;
	private String email;
	private String identity;
	private String followersNum;
	private String followingNum;
	private String MD5ID;
	
	public UserBean(){
		super();
	}
	
	
	public UserBean(String id, String nickname, String account, String password, String icon, String email, String identity, String MD5ID) {
		this.id = id;
		this.nickname = nickname;
		this.account = account;
		this.password = password;
		this.icon = icon;
		this.email = email;
		this.identity = identity;
		this.MD5ID = MD5ID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFollowersNum() {
		return followersNum;
	}

	public void setFollowersNum(String followersNum) {
		this.followersNum = followersNum;
	}

	public String getFollowingNum() {
		return followingNum;
	}

	public void setFollowingNum(String followingNum) {
		this.followingNum = followingNum;
	}
	
	public String getMD5ID() {
		return MD5ID;
	}

	public void setMD5ID(String mD5ID) {
		MD5ID = mD5ID;
	}
}
