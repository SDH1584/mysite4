package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;


@Repository
public class GalleryDao {
	@Autowired
	private SqlSession sqlSession;

	public List<GalleryVo> galleryList() {
		System.out.println("[GalleryDao.galleryList()]");
		
		List<GalleryVo> galleryList = sqlSession.selectList("gallery.galleryList");
		return galleryList;
	}
	public GalleryVo readImg(int gno) {

		System.out.println("GalleryDao.readImg");
		GalleryVo galleryVo = sqlSession.selectOne("gallery.readImg", gno);
		return galleryVo;
	
	}
	public int upload (GalleryVo galleryVo) {
		System.out.println("GalleryDao.insertFile()"+galleryVo);
		int count = sqlSession.insert("gallery.insImg", galleryVo);
		System.out.println(count + "건이 추가 되었습니다.");
		return count;
}

 
	public int delImg(int no) {
		int count = sqlSession.selectOne("gallery.delImg", no);
		System.out.println(count+"건이 삭제 되었습니다.");
		return count;
	}

	
	
}