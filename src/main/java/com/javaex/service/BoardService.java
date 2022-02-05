package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	//게시글 가져오기
	public List<BoardVo> getList() {
		System.out.println("[BoardService.getList()]");
		return boardDao.getList();
	}
	
	//게시글 읽기
	public BoardVo getRead(int no) {
		System.out.println("[BoardService.getRead()]");
		boardDao.hitup(no);
		return boardDao.getread(no);
	}
	
	//게시글 작성
	public void boardInsert(BoardVo boardVo) {
		System.out.println("[BoardService.boardInsert()]");
		boardDao.boardInsert(boardVo);
	}

	//게시글 삭제
	public void boardDelete(int no) {
		System.out.println("[BoardService.boardDelete()]");
		boardDao.boardDelete(no);
	}
	//게시글 수정
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("[BoardService.boardUpdate()]");
		boardDao.boardUpdate(boardVo);
	}
}
