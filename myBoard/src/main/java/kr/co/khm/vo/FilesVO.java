package kr.co.khm.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FilesVO {

	private int filesSeq;					// 파일 번호
	private int filesNo;					// 파일 순번
	private String filesOrgnlName;			// 원본 파일명
	private String filesStrgName;			// 저장 파일명

	// 날짜형 입력 받을때 문자열로 들어오니까 꼭 필요한 패턴 지정임(Date 객체로 만듦, 출력은 상관없고)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date filesUploadDate;			// 파일 업로드 일자
	private String filesExtn;				// 파일 확장자
	private long filesSize;					// 파일 크기
	private String filesStrgPath;			// 파일 저장 경로
	
	// 사용자가 올린 사진을 저장하는 변수(파일 객체 가져오는놈)
	// jsp에서 name명이랑 맞출거임 무조건 post고 멀티파트 폼데이터해야함
	private MultipartFile[] uploadFile;

}   

