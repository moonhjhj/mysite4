package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getBoardList(){
		
		System.out.println("게시판다오다오");
		return sqlSession.selectList("board.getBoardList");
	}
	
	
	public List<BoardVo> boardView(){
		
		return sqlSession.selectList("board.boardView");
	}
	
	public void write(BoardVo boardVo) {
		System.out.println("dao "+boardVo.toString());
		sqlSession.insert("board.write", boardVo);
	}
	
	
	public void modify(BoardVo boardVo) {
		
		sqlSession.update("board.modify", boardVo);
	}
}
