package kr.co.khm.vo;

import lombok.Data;

@Data
public class UsersVO {
	private String USERS_ID;         // 회원 ID
	private String USERS_PW;         // 회원 비밀번호
	private String USERS_AUTH;       // 회원 권한(Y 일반회원, N 관리자)
	private String USERS_NAME;       // 회원 이름
	private String USERS_EMAIL;      // 회원 이메일
	private String USERS_ZIP;        // 회원 우편주소
	private String USERS_ADDRESS;    // 회원 기본주소
	private String USERS_ADDRESS2;   // 회원 상세주소
}
