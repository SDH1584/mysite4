package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;



@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;

		//유저정보 가져오기
		public UserVo selectUser(UserVo userVo) {
		System.out.println("userDao.getUser");
		System.out.println(userVo);

		UserVo authuser=sqlSession.selectOne("user.selectUser",userVo);
		return authuser;
		}
		
		// 저장 메소드(회원가입)
		public int Insert(UserVo userVo) {
			System.out.println("[UserDao.insert()");
			int count = sqlSession.insert("user.insert", userVo);
			System.out.println("["+count+"건이 등록되었습니다(UserDao)");
			return count;
		}

		//로그인된 회원정보 가져오기
		public UserVo getUser(int no) {
			System.out.println("getUser");
			return sqlSession.selectOne("user.getUser", no);
		}
		
		//회원정보수정
		public int Update(UserVo userVo) {
			System.out.println("[UserDao.update()]");
			int count = sqlSession.update("user.update", userVo);
			System.out.println("["+count+"건이 수정되었습니다(UserDao)");
			return count;
		}
		
		//아이디중복체크
		public void Compare(String id) {
			System.out.println("[UserDao.compare()]");
			sqlSession.selectOne("user.compare", id);
		}
}
