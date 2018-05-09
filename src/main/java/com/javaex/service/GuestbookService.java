package com.javaex.service;

import java.util.List;

import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao gbDao;

	/******************** ajax 적용 *******************************/
	public GuestbookVo write(GuestbookVo guestbookVo) {
		// insert, select의 조합 같은건 service가 가지고 있는다.
		// insert
		int no = gbDao.insert(guestbookVo);
		// select
		return gbDao.selectGuestBook(no);
	}

	public int deleteAjax(GuestbookVo guestbookVo) {

		int result = gbDao.deleteAjax(guestbookVo);

		if (result == 0) {
			return 0;
		} else {
			return guestbookVo.getNo();

		}

	}

	/************************************************************/
	public List<GuestbookVo> getGbList() {

		System.out.println("서비스");
		return gbDao.getGbList();
	}

	public int insertGb(GuestbookVo gbVo) {
		System.out.println("add서비스");
		return gbDao.insertGb(gbVo);
	}

	public void deleteGb(GuestbookVo gbVo) {

		gbDao.deleteGb(gbVo);
	}

}
