package kr.co.khm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.khm.mapper.BoardMapper;
import kr.co.khm.service.BoardService;
import kr.co.khm.service.FilesService;
import kr.co.khm.vo.BoardVO;
import kr.co.khm.vo.FilesVO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 김형민
 * @category 모든 게시판의 ServiceImpl class
 * @since 2024.01.30
 */

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired 
	BoardMapper boardMapper;
	
	@Autowired
	FilesService filesService;
	
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
		
		// 글쓰기전에 파일첨부 먼저 다녀옴
		List<FilesVO> filesVOList = filesService.uploadFile(boardVO.getUploadFile(), "freeBoard"); // 얘 호출해서 파일 업로드하는거
		log.info("insertFreeBoard -> filesVOList" + filesVOList);
		// uploadFile 메서드는 파일 업로드하고 해당 파일의 정보를 담음 FilesVO 객체의 리스트를 반환함
		// 이 FilesVO 객체에 업로드된 파일 정보를 설정, 리스트에 추가하고 서버에 저장함
		
		// "freeBoard"자리 매개변수가 원래 folder인데 업로드된 파일들이 저장될 폴더를 지정하는 변순데
		//  그 폴더명 분류를 freeBoard로 하고자 저렇게 넣었음
		
		
		boardVO.setFilesSeq(filesVOList.get(0).getFilesSeq());
		log.info("filesVOList -> setFilesSeq" + filesVOList.get(0).getFilesSeq());
		// 반환된 FilesVOList에서 첫번째 파일의 파일Seq 값을 가져와서
		// 게시글 객체인 boardVO의 filesSeq에 똑같이 세팅해준다.
		// 만약 여러장 넣으려면 get(0)빼고 그냥 세팅자체를 setFilesSeqlist에(filesVOList)하면될듯..?
		
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
