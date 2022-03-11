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
	
	public GalleryVo getGallery(int no) {
		System.out.println("[GalleryDao.getGallery()]");
		GalleryVo galleryVo = sqlSession.selectOne("gallery.getGallery", no);
		System.out.println(galleryVo);
		return galleryVo;
	}
	
	public int save(GalleryVo galleryVo) {
		System.out.println("[GalleryDao.save()]");
		
		int count = sqlSession.insert("gallery.galleryInsert", galleryVo);
		System.out.println(galleryVo);
		System.out.println("["+count+"건이 등록되었습니다.(GalleryDao)"+"]");
		
		return count;
	}
	
	public int delete(int no) {
		System.out.println("[GalleryDao.delete()]");
		
		int count = sqlSession.delete("galleryDelete", no);
		System.out.println("["+count+"건이 삭제되었습니다.(GalleryDao)");
		return count;
	}
}