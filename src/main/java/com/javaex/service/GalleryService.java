package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
@Autowired
GalleryDao galleryDao;
	
	public String upload(MultipartFile file) {
		System.out.println("galleryService.upload");
		String saveDir = "C:\\javaStudy\\upload";

		// 파일관련 정보 추출

		// 원본파일이름
		String orgName = file.getOriginalFilename();

		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		// 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

		// 파일패스 생성
		String filePath = saveDir + "\\" + saveName;

		// 파일 사이즈
		long fileSize = file.getSize();

		// ***파일 저장(업로드)
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return saveName;
	}
	public GalleryVo getGallery(int no) {
		System.out.println("[GalleryService.getGallery()]");
		return getGallery(no);
	}

	public GalleryVo readImage(int gno) {
		System.out.println("GalleryService.readImage ");
		GalleryVo galleryVo = galleryDao.readImage(gno);
		 return galleryVo;
	}
	
	public void insImage(GalleryVo galleryVo) {
		System.out.println("GalleryService.insImage ");
		galleryDao.insImage(galleryVo);
	}
	
	public void delImage(GalleryVo galleryVo) {
		System.out.println("GalleryService.delImage ");
		galleryDao.delImage(galleryVo);
	}
}
