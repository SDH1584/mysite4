package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	// 게시글 가져오기
	public List<BoardVo> getBoardList() {
		System.out.println("[BoardService.getList()]");
		return boardDao.getBoardList();
	}

	// 게시글 읽기
	public BoardVo getRead(int no) {
		System.out.println("[BoardService.getRead()]");
		boardDao.hitup(no);
		return boardDao.getread(no);
	}

	// 게시글 작성
	public void boardInsert(BoardVo boardVo) {
		System.out.println("[BoardService.boardInsert()]");
		boardDao.boardInsert(boardVo);
		/*
		 * // 페이징 데이타 추가 123개 for(int i=1; i<=123;i++) { boardVo.setTitle(
		 * i+"번째 글제목입니다"); boardVo.setContent(i+"번째글 내용입니다"); boardVo.setHit(0);
		 * boardVo.setUserNo(1); boardDao.boardInsert(boardVo);
		 * 
		 * }
		 */
	}

	// 페이징 작성
	public Map<String, Object> getBoardList2(int crtPage) {
		System.out.println("boardservice/getboardlist2");
		/////////////////////
		// 리스트가져오기//
		////////////////////

		// 페이지당 글갯수
		int listCnt = 10;

		// 현재 페이지 처리
		//삼항연산자 0보다 크면 crtPage그대로 0보다 작으면 1
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);

		// 시작글 번호 1-->1 6-->51
		int startRnum = ((crtPage - 1) * listCnt) + 1;
		// 끝글번호
		int endRnum = (startRnum + listCnt) - 1;
		

		List<BoardVo> boardList = boardDao.getBoardList2(startRnum, endRnum);
		
		// 전체 글갯수가져오기
		int totalCnt = boardDao.selectTotal();
		System.out.println("totalCnt=" + totalCnt);
		// 페이지당 버튼 갯수
		int pageBtnCount = 5;

		// 마지막 버튼 번호
		// 1 1~5 0.2
		// 2 1~5 0.4
		// 5 1~5 1
		// 6 6~10 1.2
		// 11 11~15
		int endPageBtnNo = (int) (Math.ceil(crtPage / (double) pageBtnCount)) * pageBtnCount;

		// 시작버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);

		// 다음화살표 유무
		boolean next = false;
		if (endPageBtnNo * listCnt < totalCnt) {
			next = true;
		}else {// 다음화살표가 안보이면 마지막버튼값 재계산
			endPageBtnNo = (int) Math.ceil(totalCnt / (double) listCnt);
		}
		// 이전화살표 유무
		boolean prev = false;
		if (startPageBtnNo != 1) {
			prev = true;
		} 

		/////////////
		// 포장//
		///////////////
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
		System.out.print(pMap);
		
		return pMap;

	}

	// 게시글 삭제
	public void boardDelete(int no) {
		System.out.println("[BoardService.boardDelete()]");
		boardDao.boardDelete(no);
	}

	// 게시글 수정
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("[BoardService.boardUpdate()]");
		boardDao.boardUpdate(boardVo);
	}
}
