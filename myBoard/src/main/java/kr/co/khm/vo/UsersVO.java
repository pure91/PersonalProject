package kr.co.khm.vo;

import lombok.Data;

@Data
public class UsersVO extends FilesVO{
	private int usersNo;			// 회원 고유번호
	private String usersId;         // 회원 ID
	private String usersPw;         // 회원 비밀번호
	private String usersAuth;       // 회원 권한(Y 일반회원, N 관리자)
	private String usersName;       // 회원 이름
	private String usersEmail;      // 회원 이메일
	private String usersZip;        // 회원 우편주소
	private String usersAddress;    // 회원 기본주소
	private String usersAddress2;   // 회원 상세주소
	private String usersGender;		// 회원 성별
	private String usersTel;		// 회원 연락처
	
	private String usersStatus;     // 나중에 DB에 추가할지 다시 보자.. 상태 등록해서 회원 탈퇴 관리해야될지
}
