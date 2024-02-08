package kr.co.khm.mapper;

import kr.co.khm.vo.FilesVO;

public interface FilesMapper {
	
	/**
	 * 파일 업로드 시 filesSeq를 공통적으로 적용해서 사용할 것임
	 * db입력전에 먼저 pk를 가져오기위함
	 * @return 생성된 파일번호
	 */
	public int getFilesSeq();

	
	/**
	 * 파일 업로드
	 * @param filesVO
	 * @return
	 */
	public int insertFiles(FilesVO filesVO);

}
