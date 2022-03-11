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
	private GalleryDao galleryDao;

	public List<GalleryVo> list() {
		System.out.println("[GalleryService.list()]");
		
		return galleryDao.getList();
	}
	public int restore(MultipartFile file, GalleryVo galleryVo) {
		System.out.println("[GalleryService.restore()]");
	
		String saveDir = "C:\\javaStudy\\upload\\";

		//원본파일이름
		String orgName = file.getOriginalFilename();
		galleryVo.setOrgName(orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		//저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		galleryVo.setSaveName(saveName);
		
		//파일패스(저장될 장소)
		String filePath = saveDir +"\\"+ saveName;
		galleryVo.setFilePath(filePath);
		
		//파일사이즈,크기
		long fileSize = file.getSize();
		galleryVo.setFileSize(fileSize);
		
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return galleryDao.save(galleryVo);
	}
	
	public GalleryVo getGallery(int no) {
		System.out.println("[GalleryService.getGallery()]");
		return galleryDao.getGallery(no);
	}
	
	public int delete(int no) {
		System.out.println("[GalleryService.delete()]");
		return galleryDao.delete(no);
	}
}