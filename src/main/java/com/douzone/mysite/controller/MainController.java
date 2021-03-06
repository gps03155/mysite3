package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JSONResult;
import com.douzone.mysite.repository.GuestBookDao;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	@Autowired
	private GuestBookDao dao;
	
	@Autowired
	private SiteService siteService;

	@RequestMapping({"", "/main"})
	public String main(Model model) {
		/*
		GuestBookVo vo = new GuestBookVo();
		vo.setName("안대혁");
		vo.setPassword("1234");
		vo.setMessage("test");
		long no = dao.insert(vo.getName(), vo.getPassword(), vo.getMessage());
		System.out.println(no);
		*/
		
		SiteVo siteVo = siteService.select();
		
		model.addAttribute("site", siteVo);
		
		return "main/index"; // ViewResolver - prefix : /WEB-INF/views , suffix : .jsp
							 // ViewResolver - /WEB-INF/views/main/index.jsp로 경로가 설정됨
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "<h1>안녕하세요</h1>";
	}
	
	@ResponseBody
	@RequestMapping("/hello2")
	public JSONResult hello2() {
		JSONResult jsonResult = JSONResult.success(new UserVo());
		
		return jsonResult;
	}
}
