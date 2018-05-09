package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/joinform", method = RequestMethod.GET)
	public String joinform() {
		System.out.println("조인폼");
		// return "/WEB-INF/views/user/joinform.jsp";
		return "user/joinform";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("조인페이지~");
		System.out.println(userVo.toString());
		userService.join(userVo);

		// return "/WEB-INF/views/user/joinsuccess.jsp";
		return "user/joinsuccess";
	}
	// @RequestMapping(value = "/user/joinform", method = RequestMethod.GET)
	// public String joinform() {
	// System.out.println("조인폼");
	// return "/WEB-INF/views/user/joinform.jsp";
	// }
	//
	// @RequestMapping(value = "/user/join", method = RequestMethod.GET)
	// public String join(@ModelAttribute UserVo userVo) {
	// System.out.println("조인페이지~");
	// System.out.println(userVo.toString());
	// return "";
	// }

	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginform() {
		System.out.println("로긴폼");
		return "user/loginform";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {

		UserVo authUser = userService.login(email, password);
		// String url;
		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			System.out.println(authUser.toString());
			// url = "redirect:/main";
			return "redirect:/main";
		} else {
			// url = "redirect:/user/loginform";
			return "redirect:/user/loginform?result=fail";
		}
		// return url;
	}

	/******************* 로그아웃 **********************/
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/main";
	}

	/******************* 회원정보 수정폼 **********************/
	@RequestMapping(value = "/modifyform", method = RequestMethod.GET)
	public String modifyform(HttpSession session, Model model) {

		UserVo authVo = (UserVo) session.getAttribute("authUser");
		UserVo userVo = userService.modifyform(authVo.getNo());
		// System.out.println(userVo.toString());

		model.addAttribute("userVo", userVo);
		// model은 return해주는 페이지에서만 한번 불러올 수 있다.(modifyform.jsp에서 불러오기 위한 용도)
		System.out.println(userVo.toString());
		return "user/modifyform";
	}

	/******************* 회원정보 수정 **********************/
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(String name, @ModelAttribute UserVo userVo, HttpSession session) {
		// modelAttribute는 선언하는 순간 vo에 담긴 값들을 UserVo형태로 샥샥샥 넣어줌
		// @RequestParam("name") String name & String name
		// UserVo authUserName = (UserVo)session.getAttribute("authUser");
		userService.modify(userVo);

		/*****************************************************/
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		// authUser.setName(userVo.getName());
		authUser.setName(name);
		session.setAttribute("authUser", authUser);
		System.out.println(authUser.toString());
		/*****************************************************/

		// session.setAttribute("authUser", userVo);
		return "redirect:/main";
	}

	/**************** Ajax email중복체크! ******************/
	@ResponseBody // body에 데이터가 있다! (기존 방식으로 해석하지 말라고 써줌)
	@RequestMapping(value = "/emailcheck", method = RequestMethod.GET)
	public boolean exists(@RequestParam("email") String email) { // email은 parameter로 옴
		System.out.println("ajax 이메일체크" + email);
		// boolean isExists = true;
//		 boolean isExists = userService.idCheck(email);
		return userService.idCheck(email);
	}

}
