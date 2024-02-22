package kr.co.khm.service;

import java.util.List;
import java.util.Map;

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
	public List<BoardVO> listFreeBoard(Map<String, Object> map);

	
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
	public List<BoardVO> freeDetail(int freeSeq);


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


	/**
	 * 자유게시판 전체 글 수 가져오기(페이징 처리)
	 * @param map
	 * @return
	 */
	public int getTotal(Map<String, Object> map);

}
