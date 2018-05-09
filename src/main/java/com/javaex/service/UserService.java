package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
//	public int join(UserVo userVo) {//UserVo형태로 줘라~
//		//특별한 로직은 아직 없음. 그냥 연결만 해줌
//		//원래 if, while문 들어가는게 보통 여기임
//	}
	public int join(UserVo userVo) {//UserVo형태로 줘라~
		
		return userDao.insert(userVo);
	}
	
	public UserVo login(String email, String password) {
		return userDao.getUser(email, password);
	}
	
	public UserVo modifyform(int no) {
		System.out.println();
		return userDao.getUserForModify(no);
	}
	
	public int modify(UserVo userVo) {
		
		return userDao.update(userVo);
	}
	
	public boolean idCheck(String email) {
		int count = userDao.idCheck(email);

		boolean result;
		if (count > 0) {
			result = true;

		} else {
			result = false;
		}
		return result;
	}
	

}
