package kr.co.khm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board")
@Controller
public class BoardController {

	
	@GetMapping("/free/list")
	public String listFreeBoard() {
		return "board/free/list";
	}
	
	
	/* 자유게시판 등록
	 * @return
	 */
	@PostMapping
	public String insertFreeBoard() {
		log.info("insertFreeBoard : " + insertFreeBoard());
		return null;
	}
}
