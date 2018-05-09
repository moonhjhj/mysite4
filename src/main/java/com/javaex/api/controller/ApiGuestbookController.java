package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/api/gb")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody //body에 데이터로서 넣어놔라.
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<GuestbookVo> list() {

		System.out.println("ajax-list : guestbook");
		List<GuestbookVo> guestbookList = guestbookService.getGbList();
		System.out.println(guestbookList.toString());
		
		return guestbookList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public GuestbookVo add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("add");
		System.out.println(guestbookVo.toString());
		
		GuestbookVo vo = guestbookService.write(guestbookVo); //guestbookVo랑 다른애임.
		System.out.println("controller:" + vo.toString());
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST) 
	public int delete(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("delete");
		
		int result = guestbookService.deleteAjax(guestbookVo);
		return result;
	}
}
