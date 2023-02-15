package com.ssafy.happyhouse.service;

import java.util.Map;

public interface JwtService {

	<T> String create(String key, T data, String subject);
	Map<String, Object> get(String key);
	String getUser_id();
	boolean isUsable(String jwt);
	
}
