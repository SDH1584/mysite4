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
@RequestMapping(value = "/guest", method = { RequestMethod.GET, RequestMethod.POST })
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	//리스트	
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("addList");
		
		List<GuestbookVo> getList = guestbookService.gList();
		System.out.println(getList);
		model.addAttribute("getList", getList);
		
		return "/guest/addList";
	}
	//추가
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestbookVo gvo ) {
		System.out.println("add");
		guestbookService.getInsert(gvo);
		return "redirect:/guest/addList";
	}
	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(@RequestParam("no") int no, Model model) {
		System.out.println("deleteForm");

		model.addAttribute("no", no);

		return "/guest/deleteForm";
	}

	@RequestMapping(value="/delete", method= {RequestMethod.POST, RequestMethod.GET})
	public String delete(@ModelAttribute GuestbookVo gvo) {
		System.out.println("guest.delete");
		
		guestbookService.getDelete(gvo);
		
		return "redirect:/guest/addList";
	}

}
