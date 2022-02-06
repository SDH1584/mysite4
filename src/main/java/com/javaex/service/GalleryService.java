package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
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

	public void upload(MultipartFile file, GalleryVo galleryVo) {
		System.out.println("galleryService.upload");
		String saveDir = "C:\\javaStudy\\upload";

		// 파일관련 정보 추출

		// 원본파일이름
		String orgName = file.getOriginalFilename();
		galleryVo.setOrgName(orgName);
		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		// 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		galleryVo.setSaveName(saveName);

		// 파일패스 생성
		String filePath = saveDir + "\\" + saveName;
		galleryVo.setFilePath(filePath);

		// 파일 사이즈
		long fileSize = file.getSize();
		galleryVo.setFileSize(fileSize);

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
		galleryDao.upload(galleryVo);
	}

	// 리스트
	public List<GalleryVo> getGalleryList() {

		System.out.println("guestbookService.getGusetList()");

		return galleryDao.galleryList();
	}

	// 읽기
	public GalleryVo readImg(int gno) {
		System.out.println("GalleryService.readImg ");
		GalleryVo galleryVo = galleryDao.readImg(gno);
		return galleryVo;
	}

	// 삭제
	public int delImg(int no) {
		System.out.println("GalleryService.delImg ");
		return galleryDao.delImg(no);
	}
}
