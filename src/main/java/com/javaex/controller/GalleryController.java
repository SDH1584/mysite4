package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("/list")
	public String list() {
		System.out.println("gallery List");
		
		return "gallery/list";
	}
	@RequestMapping("upload")
	public String listupload(@RequestParam("file")MultipartFile file, Model model,HttpSession session) {
		System.out.println("gallery upload");
		
		String saveName=galleryService.upload(file);
		model.addAttribute("saveName",saveName);
		
		return "gallery/list";
		
	}
	
	@ResponseBody
	@RequestMapping("/read")
	public GalleryVo read(@RequestParam("no") int no) {
		System.out.println("[GalleryController.getGallery()]");
		GalleryVo galleryVo = galleryService.getGallery(no);
		return galleryVo;
	}
}
