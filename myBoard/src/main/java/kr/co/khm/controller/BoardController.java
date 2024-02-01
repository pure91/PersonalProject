package kr.co.khm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.khm.service.BoardService;
import kr.co.khm.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 김형민
 * @category 모든 게시판의 컨트롤러
 * @since 2024.01.30
 */

@Slf4j
@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired BoardService boardService;
	
	/* 자유게시판 목록 조회 */
	@GetMapping("/freeList")
	public String listFreeBoard(Model model) {
		
		List<BoardVO> freeBoardList = this.boardService.listFreeBoard();
		log.info("freeBoardList : " + freeBoardList);
		model.addAttribute("freeBoardList", freeBoardList);
		
		return "board/freeList";
	}
	
	/* 자유게시판 게시글 등록 */
	@PostMapping("/freeInsert")
	public String insertFreeBoard(@ModelAttribute BoardVO boardVO) {
		int insertCnt = boardService.insertFreeBoard(boardVO);
		log.info("insertFreeBoard : " + insertCnt);
		
		if (insertCnt > 0) {
			return "redirect:/board/freeList";
		} else {
			return "board/freeInsert";
		}
	}
}
