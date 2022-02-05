package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	// 방명록 리스트가져오기
	public List<GuestbookVo> gList() {
		System.out.println("guestbookService/gList");
		return guestbookDao.getList();
	}
	
	//방명록 글저장
	public int getInsert(GuestbookVo guestbookVo) {
		System.out.println(guestbookVo);
		return guestbookDao.guestInsert(guestbookVo);
	}
	
	//방명록 글삭제
	public int getDelete(GuestbookVo guestbookVo) {
		int count=guestbookDao.guestDelete(guestbookVo);
		return count;
	}
	
	//방명록글 저장 --> 저장글리턴
	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
		System.out.println("guestbookService/addGuestResultVo");
		
		//저장하기
		int count =guestbookDao.insertSelectKey(guestbookVo);
		
		//저장한 내용 가져오기
		int no = guestbookVo.getNo();
		return guestbookDao.selectGuest(no);
	}
	
	//ajax 방명록 글 삭제
	public String remove(GuestbookVo guestbookVo) {
		System.out.println("guestbookService.remove");
		int count =guestbookDao.guestDelete(guestbookVo);
		if (count>0) {
			return "success";
		}else{
			return "fail";
		}
	}
}
