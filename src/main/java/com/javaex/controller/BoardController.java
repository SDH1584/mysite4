package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/board", method = { RequestMethod.GET, RequestMethod.POST })
public class BoardController {
	@Autowired
	private BoardService boardService;

	//게시글 가져오기
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[BoardController.list()");

		List<BoardVo> boardList = boardService.getList();
		model.addAttribute("boardList", boardList);

		return "/board/list";
	}
	
	//게시글 읽기
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@RequestParam("no") int no, Model model) {
		System.out.println("[BoardController.read()");

		BoardVo boardVo = boardService.getRead(no);

		model.addAttribute("boardVo", boardVo);

		return "/board/read";
	}

	//게시글 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("[BoardController.delete()");

		boardService.boardDelete(no);

		return "redirect:/board/list";
	}

	//게시글작성폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm(HttpSession session) {
		System.out.println("[BoardController.writeForm()");
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser != null) {
			return "/board/writeForm";
		} else {
			return "redirect:/";
		}
	}

	//게시글 작성
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(HttpSession session, @RequestParam("title") String title,
			@RequestParam("content") String content) {
		System.out.println("[BoardController.write()");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContent(content);
		boardVo.setUserNo(authUser.getNo());
		boardService.boardInsert(boardVo);

		return "redirect:/board/list";
	}	
	
	//게시글 수정폼 
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("[BoardController.modifyForm()");

		BoardVo boardVo = boardService.getRead(no);

		model.addAttribute("boardVo", boardVo);

		return "/board/modifyForm";
	}

	//게시글수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("[BoardController.modify()");

		boardService.boardUpdate(boardVo);

		return "redirect:/board/list";
	}
}
