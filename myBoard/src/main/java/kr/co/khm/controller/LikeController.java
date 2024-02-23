package kr.co.khm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.khm.service.LikeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 김형민
 * @category 게시판 좋아요 컨트롤러
 * @since 2024.02.23
 */

@RequestMapping("like")
@Slf4j
@Controller
public class LikeController {
	
	@Autowired
	LikeService likeService;
	
	/** 자유게시판 좋아요 버튼
	 * @param freeSeq
	 * @param usersId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("board/updateLike")
	public int updateLike(int freeSeq, String usersId)throws Exception{
		
		int likeCheck = likeService.likeCheck(freeSeq, usersId);
		
		if(likeCheck == 0) {
			// 카운트 0 잡히면 좋아요 처음 누르는거(db에 default 0으로 해놨음)
			likeService.insertLike(freeSeq, usersId); 		// like 테이블 삽입
			likeService.updateLike(freeSeq);				// 게시판 테이블 + 1
			likeService.updateLikeCheck(freeSeq, usersId);	// like 테이블 구분자 1
		} else if(likeCheck == 1) {
			likeService.updateLikeCheckCancel(freeSeq, usersId); // like 테이블 구분자 0
			likeService.updateLikeCancle(freeSeq);				 // 게시판 테이블 - 1
			likeService.deleteLike(freeSeq, usersId);			 // likde 테이블 삭제
		}
		return likeCheck;
	}
}
