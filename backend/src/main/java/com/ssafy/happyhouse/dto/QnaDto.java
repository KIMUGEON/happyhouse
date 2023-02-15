package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Qna (게시글정보)", description = "글번호, 제목, 내용, 작성자아이디, 작성일을 가진   Domain Class")
public class QnaDto {
	@ApiModelProperty(value = "글번호")
	private int articleno;
	@ApiModelProperty(value = "작성자아이디")
	private String user_id;
	@ApiModelProperty(value = "제목")
	private String subject;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "조회수")
	private int hit;
	@ApiModelProperty(value = "작성일")
	private String regtime;

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public QnaDto(int articleno, String user_id, String subject, String content, int hit, String regtime) {
		super();
		this.articleno = articleno;
		this.user_id = user_id;
		this.subject = subject;
		this.content = content;
		this.hit = hit;
		this.regtime = regtime;
	}

}