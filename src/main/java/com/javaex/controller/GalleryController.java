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
@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	//리스틍 읽어오기
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("gallery List");
		List<GalleryVo> galleryList = galleryService.getGalleryList();
		
		model.addAttribute("galleryList", galleryList);
		
		return "gallery/list";
	}
	//사진업로드
	@RequestMapping("upload")
	public String upload(@ModelAttribute GalleryVo galleryVo,@RequestParam("file")MultipartFile file, Model model,HttpSession session) {
		System.out.println("gallery upload");
		
		galleryService.upload(file, galleryVo);
			
		return "gallery/list";
		
	}
	//사진 읽어오기
	@ResponseBody
	@RequestMapping("/read")
	public GalleryVo read(@RequestParam("no") int no) {
		System.out.println("[GalleryController.getGallery()]");
		GalleryVo galleryVo = galleryService.readImg(no);
		return galleryVo;
	}
	//삭제
	@ResponseBody
	@RequestMapping("/remove")
	public int remove(@RequestParam("no") int no) {
		
		System.out.println("remove()"); 
			return  galleryService.delImg(no);
	}
	
}
