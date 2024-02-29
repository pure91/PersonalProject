package kr.co.khm.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * @author 김형민
 * @category 자유게시판 VO
 * @since 2024.01.30
 */

@Data
public class BoardVO extends FilesVO{
	private int freeSeq;         // 게시글 번호
	private String usersId;      // 회원 ID
	private String usersName;    // 작성자
	private String freeTitle;    // 게시글 제목
	private String freeContents; // 게시글 내용
	private int freeCnt;         // 게시글 조회수
	private Date freeWrtDate;    // 작성일자
	private Date freeMdfDate;    // 수정일자
	private int filesSeq;		 // 파일 번호
	private int likeHit;		 // 게시글 좋아요 수
	
	// 사용자가 올린 파일을 저장하는 변수(파일 객체 가져오는 놈)
	// 상속돼있어도 똑같이 만들어야하는듯
    private MultipartFile[] uploadFile;
}

