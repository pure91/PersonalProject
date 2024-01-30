package kr.co.khm.mapper;

import java.util.List;

import kr.co.khm.vo.BoardVO;

/**
 * @author 김형민
 * @category 모든 게시판의 Mapper interface
 * @since 2024.01.30
 */

public interface BoardMapper {

	
	/**
	 * 자유게시판 조회
	 */
	public List<BoardVO> listFreeBoard();

	/**
	 * 자유게시판 글 등록
	 * @param boardVO
	 * @return
	 */
	public int insertFreeBoard(BoardVO boardVO);

}
