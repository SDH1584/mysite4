package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	//방명록 리스트 가져오기
	public List<GuestbookVo> getList() {
		System.out.println("[GuestbookDao.getList()]");
		return sqlSession.selectList("guestbook.selectList");
	}
	
	//방명록 글 저장
	public int guestInsert(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookDao.guestbookInsert()");
		
		int count = sqlSession.insert("guestbook.insert", guestbookVo);
		System.out.println("["+count+"건이 생성되었습니다]");
		return count;
	}

	//방명록 글 삭제
	public int guestDelete(GuestbookVo guestbookVo) {
		int count = sqlSession.delete("guestbook.delete", guestbookVo);
		System.out.println("["+count+"건이 삭제되었습니다]");
		
		return count;
	}
	
	
	//방명록 글 저장(selectKey)   //리턴 성공한 갯수
	public int insertSelectKey(GuestbookVo guestbookVo){
		System.out.println("insertselectkey");
		System.out.println(guestbookVo);
		
		return sqlSession.insert("guestbook.insertSelectKey",guestbookVo);
	}
	
	//방명록 글1개 가져오기
	   public GuestbookVo selectGuest(int no) {
	      System.out.println("guestbookDao/selectGuest");
	      return sqlSession.selectOne("guestbook.selectByNo", no);
	   }

	
}