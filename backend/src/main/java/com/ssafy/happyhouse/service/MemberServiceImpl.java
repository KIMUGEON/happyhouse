package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.mapper.MemberMapper;



@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUser_id() == null || memberDto.getUser_password() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String user_id) throws Exception {
		return memberMapper.userInfo(user_id);
	}

	@Override
	public boolean regist(MemberDto memberDto) {
		return memberMapper.regist(memberDto);
	}

	@Override
	public boolean updateUser(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return memberMapper.updateUser(memberDto);
	}

	@Override
	public boolean deleteUser(String user_id) {
		// TODO Auto-generated method stub
		return memberMapper.deleteUser(user_id);
	}

	@Override
	public MemberDto idCheck(MemberDto memberDto) {
		// TODO Auto-generated method stub
		if(memberDto.getUser_id() == null)
			return null;
		return memberMapper.idCheck(memberDto);
	}

	@Override
	public MemberDto isAdmin(String user_id) {
		// TODO Auto-generated method stub
		return memberMapper.isAdmin(user_id);
	}

	@Override
	public List<MemberDto> retrieveUser() {
		// TODO Auto-generated method stub
		return memberMapper.selectUser();
	}

}
