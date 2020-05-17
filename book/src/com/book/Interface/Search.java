package com.book.Interface;
import com.alibaba.fastjson.JSONObject;
public interface Search {
	JSONObject search(String page,String keyword,String booktype,String searchType);
	JSONObject search(String page,String keyword,String searchType);
}
