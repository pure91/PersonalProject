package kr.co.khm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.khm.service.BoardService;
import kr.co.khm.util.ArticlePage;
import kr.co.khm.vo.BoardVO;
import kr.co.khm.vo.UsersVO;
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
	public String listFreeBoard(Model model,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "searchType", required = false) String searchType) {
			//키워드는 나중에 util 폴더 만들어서 articlepage랑 paginghandler만들기
			log.info("currentPage: " + currentPage);
			log.info("keyword", keyword);
			log.info("list->searchType : " + keyword);
		
		// 현재 페이지랑, 키워드(검색용)도 넣어줄꺼라 map으로 생성하는게 편할듯
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage);
		map.put("keyword", keyword);
		map.put("searchType",searchType);
		log.info("freeList - >map : " + map);
		
		// 위에서 페이지 담아서 리스트 조회하기
		List<BoardVO> freeBoardList = this.boardService.listFreeBoard(map);
		log.info("freeBoardList : " + freeBoardList);
		
		// 전체 글 수 가져오기
		int total = boardService.getTotal(map);
		log.info("listFreeBoard -> total : " + total);
		
		// 페이지네이션 Util 메서드
		ArticlePage<BoardVO> articlePage = new ArticlePage<BoardVO>(total, currentPage, 10, freeBoardList);
		
		articlePage.setUrl("/board/free/freeList");
		articlePage.setKeyword(keyword);
		articlePage.setSearchType(searchType);
		
		model.addAttribute("articlePage", articlePage);
		model.addAttribute("freeBoardList", freeBoardList);
		
		return "board/freeList";
	}
	
	/* 자유게시판 게시글 등록 폼 이동 */
	@GetMapping("/freeInsert")
	public String insertFreeBoardForm() {
		log.info("insertFreeBoard 폼으로 이동");
		return "board/freeInsert";
	}
	
	
	/* 자유게시판 게시글 등록 */
	@PostMapping("/freeInsert")
	public String insertFreeBoard(@ModelAttribute BoardVO boardVO, HttpSession session) {
		UsersVO loggedInUser = (UsersVO) session.getAttribute("login");

		if(loggedInUser != null) {
			String usersId = loggedInUser.getUsersId();
			String usersName = loggedInUser.getUsersName();
			boardVO.setUsersId(usersId);
			boardVO.setUsersName(usersName);
		}
		
		int insertCnt = boardService.insertFreeBoard(boardVO);
		log.info("insertFreeBoard : " + insertCnt);
		
		if (insertCnt > 0) {
			return "redirect:/board/freeList";
		} else {
			return "board/freeInsert";
		}
	}
	
	/* 자유게시판 상세 */
	@GetMapping("/freeDetail")
	public String freeDetail(@RequestParam("freeSeq") int freeSeq, Model model) {
		// 상세 누르면 조회수 올리기
		boardService.updateCnt(freeSeq);
		
		BoardVO boardVO = boardService.freeDetail(freeSeq);
		model.addAttribute("board", boardVO);
		return "board/freeDetail";
	}
	
	/* 자유게시판 글 수정 -> 글 수정 폼으로 보내기 */
	// 다들 그.. 겟버튼 눌러 오는거라 겟매핑임
	@GetMapping("/freeUpdate")
	public String updateForm(@RequestParam("freeSeq") int freeSeq, Model model) {
		
		BoardVO boardVO = boardService.freeDetail(freeSeq);
		//수정은 DB에서 값을 가져와야하니 model필요
		model.addAttribute("board", boardVO);
		return "board/freeUpdate";
	}
	
	/* 자유게시판 폼에서 실제 글 수정 */
	@PostMapping("freeUpdate")
	public String update(@ModelAttribute BoardVO boardVO, Model model) {
		boardService.update(boardVO);
		
		// 글 수정했으면 다시 회원 id를 찾아, 수정 누르면 그 회원 id가 해당하는 디테일로 바로 보여주려고~
		BoardVO boardVO2 = boardService.freeDetail(boardVO.getFreeSeq());
		model.addAttribute("board", boardVO2);
		
		return "board/freeDetail";
	}
	
	/* 자유게시판 글 삭제 */
	@GetMapping("/freeDelete")
	public String delete(@RequestParam("freeSeq") int freeSeq) {
		boardService.delete(freeSeq);
		return "redirect:/board/freeList";
	}
	
	/* 페이징 임시(2024.02.04) */
	// 첫페이지는 1페이지를 보여줌
//	@GetMapping("/paging")
//	public String paging(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
//		log.info("page : " + page);
//		return "board/freeList";
//	}
}
