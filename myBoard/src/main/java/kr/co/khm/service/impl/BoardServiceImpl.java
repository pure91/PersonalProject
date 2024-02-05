package kr.co.khm.service.impl;

import java.util.List;
import java.util.Map;

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
	public List<BoardVO> listFreeBoard(Map<String, Object> map) {
		return boardMapper.listFreeBoard(map);
	}

	/**
	 * 자유게시판 글 등록
	 */
	@Override
	public int insertFreeBoard(BoardVO boardVO) {
		return boardMapper.insertFreeBoard(boardVO);
	}

	/**
	 * 자유게시판 디테일
	 */
	@Override
	public BoardVO freeDetail(int freeSeq) {
		return boardMapper.freeDetail(freeSeq);
	}

	
	/**
	 * 자유게시판 조회수
	 */
	@Override
	public void updateCnt(int freeSeq) {
		boardMapper.updateCnt(freeSeq);
	}

	
	/**
	 * 자유게시판 글 삭제
	 */
	@Override
	public void delete(int freeSeq) {
		boardMapper.delete(freeSeq);
	}

	/**
	 * 자유게시판 글 수정
	 */
	@Override
	public void update(BoardVO boardVO) {
		boardMapper.update(boardVO);
	}

	
	/**
	 * 자유게시판 전체 글 수 가져오기(페이징 처리)
	 */
	@Override
	public int getTotal(Map<String, Object> map) {
		return boardMapper.getTotal(map);
	}

}
