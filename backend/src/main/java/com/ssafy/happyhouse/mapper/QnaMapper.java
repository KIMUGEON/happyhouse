package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.QnaDto;
@Mapper
public interface QnaMapper {
	public List<QnaDto> selectQna();
	public QnaDto selectQnaByNo(int articleno);
	public int insertQna(QnaDto qna);
	public int updateQna(QnaDto qna);
	public int deleteQna(int articleno);
	
	public int updateQnaCnt(int articleno);
}