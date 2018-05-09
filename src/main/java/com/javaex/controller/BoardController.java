package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/list")
	public String boardList(Model model) {
		
		List<BoardVo> list = boardService.getBoardList();
		model.addAttribute("list", list);
		System.out.println("게시판컨트롤러");
		System.out.println(list.toString());
		return "board/list";

	}
	
	@RequestMapping(value = "/view")
	public String view(Model model) {
		
		BoardVo vo = boardService.boardView();
		model.addAttribute("viewList", viewList);
		return "board/view";
	}
	
	@RequestMapping(value = "/writeform", method = RequestMethod.GET)
	public String writeform() {
		System.out.println("board_writeform");
		return "board/write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		boardVo.setUser_no(authUser.getNo());
		boardService.write(boardVo);
		return "redirect:/board/list";
	}
	
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@ModelAttribute BoardVo boardVo) {
		
		boardService.modify(boardVo);
		return "board/view";
	}
}
