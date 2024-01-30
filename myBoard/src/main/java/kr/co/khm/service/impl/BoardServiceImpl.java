package kr.co.khm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.khm.mapper.BoardMapper;
import kr.co.khm.service.BoardService;
import kr.co.khm.vo.BoardVO;

/**
 * @author 김형민
 * @category 모든 게시판의 ServiceImpl class
 * @since 2024.01.30
 */

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired BoardMapper boardMapper;
	
	/**
	 * 자유게시판 조회
	 */
	@Override
	public List<BoardVO> listFreeBoard() {
		return boardMapper.listFreeBoard();
	}

	/**
	 * 자유게시판 글 등록
	 */
	@Override
	public int insertFreeBoard(BoardVO boardVO) {
		return boardMapper.insertFreeBoard(boardVO);
	}

}
