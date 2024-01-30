package kr.co.khm.vo;

import java.util.Date;

import lombok.Data;

/**
 * @author 김형민
 * @category 자유게시판 VO
 * @since 2024.01.30
 */

@Data
public class BoardVO {
	private int freeSeq;         // 게시글 번호
	private String usersId;      // 회원 ID
	private String usersName;    // 작성자
	private String freeTitle;    // 게시글 제목
	private String freeContents; // 게시글 내용
	private int freeCnt;         // 게시글 조회수
	private Date freeWrtDate;    // 작성일자
	private Date freeMdfDate;    // 수정일자
	private int filesSeq;        // 파일번호
	
}
