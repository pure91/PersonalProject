package kr.co.khm.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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

//	// 날짜형 입력 받을때 문자열로 들어오니까 꼭 필요한 패턴 지정임(Date 객체로 만듦, 출력은 상관없고)
//	// 아 근데 자동으로 sysdate 칠거면 상관없나보다
//	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date freeWrtDate;    // 작성일자
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date freeMdfDate;    // 수정일자
//	private int filesSeq;        // 파일번호
	
	// 사용자가 올린 사진을 저장하는 변수
	// jsp에서 name명이랑 맞출거임 무조건 post고 멀티파트 폼데이터해야함
	private MultipartFile filesSeq;
	
}
