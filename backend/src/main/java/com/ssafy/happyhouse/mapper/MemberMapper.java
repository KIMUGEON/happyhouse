package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.MemberDto;




public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String user_id) throws SQLException;
	public boolean regist(MemberDto memberDto);
	public boolean updateUser(MemberDto memberDto);
	public boolean deleteUser(String user_id);
	
	public MemberDto idCheck(MemberDto memberDto);
	public MemberDto isAdmin(String user_id);
	
	public List<MemberDto> selectUser();
	public List<MemberDto> listUser();
	
}