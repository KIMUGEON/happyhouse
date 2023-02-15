package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.MemberDto;


public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String user_id) throws Exception;
	public boolean regist(MemberDto memberDto);
	public boolean updateUser(MemberDto memberDto);
	public boolean deleteUser(String user_id);
	
	
	public MemberDto idCheck(MemberDto memberDto);
	public MemberDto isAdmin(String user_id);
	
	public List<MemberDto> retrieveUser();
}
