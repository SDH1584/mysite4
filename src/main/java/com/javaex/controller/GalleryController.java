package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("[GalleryController.list()]");

		List<GalleryVo> galleryList = galleryService.list();
		model.addAttribute("galleryList", galleryList);

		return "/gallery/list";
	}

	@RequestMapping("/upload")
	public String upload(MultipartFile file, HttpSession session, @ModelAttribute GalleryVo galleryVo) {
		System.out.println("[GalleryController.upload()]");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser != null) {
			galleryVo.setUserNo(authUser.getNo());
			galleryService.restore(file, galleryVo);
			return "redirect:/gallery/list";
		} else {
			return "redirect:/";
		}
	}

	@ResponseBody
	@RequestMapping("/read")
	public GalleryVo read(@RequestParam("no") int no) {
		System.out.println("[GalleryController.getGallery()]");
		GalleryVo galleryVo = galleryService.getGallery(no);
		return galleryVo;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(@RequestParam("no") int no) {
		System.out.println("[GalleryController.delete()]");
		return galleryService.delete(no);
	}
}