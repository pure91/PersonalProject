package kr.co.khm.service;

import java.util.List;

import kr.co.khm.vo.BoardVO;

/**
 * @author 김형민
 * @category 모든 게시판의 Service interface
 * @since 2024.01.30
 */

public interface BoardService {

	
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
	
	
	/**
	 * 자유게시판 디테일
	 * @param freeSeq
	 * @return
	 */
	public BoardVO freeDetail(int freeSeq);


	/**
	 * 자유게시판 조회수
	 * @param freeSeq
	 * @return 
	 */
	public void updateCnt(int freeSeq);


	/**
	 * 자유게시판 글 삭제
	 * @param freeSeq
	 */
	public void delete(int freeSeq);


	/**
	 * 자유게시판 글 수정
	 * @param boardVO
	 */
	public void update(BoardVO boardVO);

}
