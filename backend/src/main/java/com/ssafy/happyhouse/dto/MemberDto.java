package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class MemberDto {

	@ApiModelProperty(value = "회원 아이디")
	private String user_id;
	@ApiModelProperty(value = "회원 비밀번호")
	private String user_password;
	@ApiModelProperty(value = "회원 이름")
	private String user_name;
	@ApiModelProperty(value = "회원 이메일")
	private String user_email;
	@ApiModelProperty(value = "회원 가입일")
	private String joindate;
	@ApiModelProperty(value = "회원 토큰")
	private String token;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "MemberDto [user_id=" + user_id + ", user_password=" + user_password + ", user_name=" + user_name
				+ ", user_email=" + user_email + ", joindate=" + joindate + ", token=" + token + "]";
	}
	
	
	

}
