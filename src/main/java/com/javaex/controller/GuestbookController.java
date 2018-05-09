package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/gb", method = RequestMethod.POST)
public class GuestbookController {

	@Autowired
	private GuestbookService gbService;

	/******************** ajax 적용 *******************************/

	@RequestMapping(value = "/list-ajax", method = RequestMethod.GET)
	public String ajaxList() {
		System.out.println("list-ajax");
		return "guestbook/ajax-list";
	}
	/************************************************************/

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<GuestbookVo> list = gbService.getGbList();
		model.addAttribute("list", list);
		// System.out.println(list);
		return "guestbook/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@ModelAttribute GuestbookVo gbVo) {
		int count = gbService.insertGb(gbVo);
		if (count != 0) {
			System.out.println("add컨트롤러");
			System.out.println(gbVo.toString());
		}
		return "redirect:/gb/list";
	}

	@RequestMapping(value = "/deleteform", method = RequestMethod.GET)
	public String deleteform(Model model, @RequestParam("no") String no) {
		model.addAttribute("no", no);

		return "guestbook/deleteform";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@ModelAttribute GuestbookVo gbVo) {

		System.out.println(gbVo.toString());

		gbService.deleteGb(gbVo);
		return "redirect:/gb/list";
	}

}
