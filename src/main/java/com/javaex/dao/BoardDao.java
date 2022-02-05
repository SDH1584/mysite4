package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;

	//리스트가져오기
	public List<BoardVo> getList() {
		System.out.println("[BoardDao.getList()]");
		
		return sqlSession.selectList("board.getList");
	}
	
	//선택 게시글 읽기
		public BoardVo getread(int no) {
			System.out.println("[BoardDao.getRead()]");
			return sqlSession.selectOne("board.getRead", no);
		}
	//조회수상승	
		public void hitup(int no) {
			System.out.println("[BoardDao.hitup()]");
			sqlSession.update("board.hitup", no);
		}	

	//게시글 등록
	public void boardInsert(BoardVo boardVo) {
		System.out.println("[BoardDao.boardInsert()]");
		int count = sqlSession.insert("board.boardInsert", boardVo);
		System.out.println("["+count+"건이 등록되었습니다]");
	}
	//게시글 삭제
	public void boardDelete(int num) {
		System.out.println("[BoardDao.boardDelete()]");
		int count = sqlSession.delete("board.boardDelete", num);
		System.out.println("["+count+"건이 삭제되었습니다]");
	}
	//게시글 수정
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("[BoardDao.boardUpdate()]");
		System.out.println(boardVo);
		int count = sqlSession.update("board.boardUpdate", boardVo);
		System.out.println("["+count+"건이 수정되었습니다]");
	}	
		
}
