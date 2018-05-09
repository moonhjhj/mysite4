package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo userVo) {
		//Dao의 insert와 sqlsession의 insert는 다른 것.
		return sqlSession.insert("user.insert", userVo); //(sql문 이름, 데이터)
		
	}
	
	public UserVo getUser(String email, String password) {
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("email", email);
		userMap.put("password", password);
		return sqlSession.selectOne("user.selectUserByEmailPw", userMap);
		
	}
	
	public UserVo getUserForModify(int no) {
		
		return sqlSession.selectOne("user.selectUserForModify", no);
	}
	
	public int update(UserVo userVo) {
		
		return sqlSession.update("user.update", userVo);
	}
	
	public int idCheck(String email) {
		
		return sqlSession.selectOne("user.idCheck", email);
	}
}
