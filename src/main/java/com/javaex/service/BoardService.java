package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getBoardList() {
		System.out.println("게시판써비쓰");
		return boardDao.getBoardList();
	}
	
	public List<BoardVo> boardView(){
		
		return boardDao.boardView();
	}
	
	public void write(BoardVo boardVo) {
		
		boardDao.write(boardVo);
	}
	
	
	public void modify(BoardVo boardVo) {
		
		boardDao.modify(boardVo);
	}
}
