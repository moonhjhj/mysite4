package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FIleUploadService;
import com.javaex.vo.FileVo;

@Controller
@RequestMapping(value = "/gallery", method = RequestMethod.GET)
public class GalleryController {

	@Autowired
	private FIleUploadService fileUploadService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(Model model) {
		
		List<FileVo> fileList = fileUploadService.getList();
		
		model.addAttribute("fileList", fileList);
		return "gallery/list";
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String insert(@RequestParam("file") MultipartFile file, Model model) {
		
		fileUploadService.restore(file);
		return "redirect:/gallery/list";
	}
	
}
