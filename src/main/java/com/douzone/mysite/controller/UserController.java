package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;
import com.douzone.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET) // 넘어오는 데이터 없을 때 : GET
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST) // form에서 데이터 넘어올 때 : POST
	public String join(@ModelAttribute UserVo userVo) {
		userService.join(userVo);
	
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/loginform";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, Model model) {//HttpSession session, Model model) {
		System.out.println(authUser);
		
		UserVo vo = userService.getUserInfo(authUser);
		model.addAttribute("vo", vo);
		
		return "user/modifyform";
	}
	
	@Auth
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@AuthUser UserVo authUser, @ModelAttribute UserVo userVo) {
		userService.modify(userVo);
		
		if(!"".equals(userVo.getName())) {
			// session.setAttribute("authuser", userVo);
			authUser.setName(userVo.getName());
		}
		
		return "redirect:/";
	}
	
	
	/*
	@ExceptionHandler(UserDaoException.class)
	public String handleUserDaoException() {
		// 1. logging
		// 2. 페이지 전환 (Error 페이지 - 사용자에게 에러 발생을 알림)
		return "error/exception";
	}
	*/
}
