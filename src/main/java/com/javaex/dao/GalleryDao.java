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

	public List<GalleryVo> getList() {
		System.out.println("[GalleryDao.getList()]");
		
		List<GalleryVo> galleryList = sqlSession.selectList("gallery.getGalleryList");
		return galleryList;
	}
	public GalleryVo readImage(int gno) {

		System.out.println("GalleryDao.readImage");
		GalleryVo galleryVo = sqlSession.selectOne("gallery.readImage", gno);
		return galleryVo;
	
	}
	public void insImage(GalleryVo galleryVo) {
		System.out.println("GalleryDao.insImage");
		sqlSession.insert("gallery.insImage");
	}
 
	public void delImage(GalleryVo galleryVo) {
		System.out.println("GalleryDao.delImage");
		sqlSession.delete("gallery.delImage");
	}
	
	
}