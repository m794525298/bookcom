package com.book.Interface;

import java.sql.ResultSet;

public interface Login {
	public ResultSet login(String account, String password);
}
