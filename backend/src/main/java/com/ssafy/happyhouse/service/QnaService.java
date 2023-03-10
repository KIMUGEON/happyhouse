package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.QnaDto;

public interface QnaService {
	public List<QnaDto> retrieveQna();
	public QnaDto detailQna(int articleno);
	public boolean writeQna(QnaDto qna);
	public boolean updateQna(QnaDto qna);
	public boolean deleteQna(int articleno);
	
	public boolean updateQnaCnt(int articleno);
}
