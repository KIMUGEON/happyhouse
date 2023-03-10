package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dto.QnaDto;
import com.ssafy.happyhouse.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {
	
    @Autowired
	private QnaMapper qnaMapper;

    @Override
	public List<QnaDto> retrieveQna() {
		return qnaMapper.selectQna();
	}
    
  	@Override
	public boolean writeQna(QnaDto qna) {
		return qnaMapper.insertQna(qna) == 1;
	}

	@Override
	public QnaDto detailQna(int articleno) {
		return qnaMapper.selectQnaByNo(articleno);
	}

	@Override
	@Transactional
	public boolean updateQna(QnaDto qna) {
		return qnaMapper.updateQna(qna) == 1;
	}

	@Override
	@Transactional
	public boolean deleteQna(int articleno) {
		return qnaMapper.deleteQna(articleno) == 1;
	}

	@Override
	public boolean updateQnaCnt(int articleno) {
		// TODO Auto-generated method stub
		return qnaMapper.updateQnaCnt(articleno) == 1;
	}
}