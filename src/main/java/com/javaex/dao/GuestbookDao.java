package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	/******************** ajax 적용 *******************************/

	public int insert(GuestbookVo guestbookVo) {
		System.out.println(guestbookVo.toString());
		sqlSession.insert("guestbook.insert", guestbookVo);
		System.out.println(guestbookVo.toString());
		return guestbookVo.getNo();
	}
	
	public GuestbookVo selectGuestBook(int no) {
		
		return sqlSession.selectOne("guestbook.selectGuestBook", no);
	}
	
	public int deleteAjax(GuestbookVo guestbookVo) {
		
		return sqlSession.delete("guestbook.deleteAjax", guestbookVo);
	}
	/************************************************************/

	public List<GuestbookVo> getGbList() {
		
		System.out.println("컨트롤러");
		return sqlSession.selectList("guestbook.selectGbList");
	}
	
	public int insertGb(GuestbookVo gbVo) {
		System.out.println("add다오다오");
		
		return sqlSession.insert("guestbook.insertGb", gbVo);
	}
	public void deleteGb(GuestbookVo gbVo) {
		System.out.println();
		sqlSession.delete("guestbook.deleteGb", gbVo);
	}
}
