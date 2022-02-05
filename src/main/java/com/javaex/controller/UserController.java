package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {

	@Autowired
	private UserService userService;

	// 로그인폶
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController.loginForm");
		return "user/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo,HttpSession session) {
		System.out.println("UserController.login");

		UserVo authUser=userService.login(userVo);
				System.out.println(authUser);

		
		if(authUser != null) {//로그인성공
			//세션에 저장
		session.setAttribute("authUser",authUser);
			//리다이렉트 메인
		return "redirect:/";
		}else {//로그인실패
			//리다이렉트 로그인폼
			return "redirect:/user/loginForm?result=fail";
		}
	}
	//로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
	System.out.println("logout");
		//세션 정보삭제
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}

	//회원가입폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserComtroller.joinForm()]");

		return "/user/joinForm";
	}

	//회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserComtroller.join()]");
		userService.join(userVo);
		return "/user/joinOk";
	}

	//회원정보수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("[UserController.modifyForm()]");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		UserVo userVo = userService.modifyForm(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "/user/modifyForm";
	}

	//회원정보수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo) {
		System.out.println("[UserComtroller.modify()]");
		userService.modify(userVo);
		return "redirect:/";
	}
	//아이디 중복 비교
	@RequestMapping(value="/compare", method = {RequestMethod.POST, RequestMethod.GET})
	public String compare(Model model, @RequestParam("compare") String id) {
		
		userService.compare(id);
		
		model.addAttribute("compare", id);
		return "redirect:/user/joinForm";
	}
	
	
}
