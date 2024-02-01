package kr.co.khm.service;

import kr.co.khm.vo.UsersVO;

/**
 * @author 김형민
 * @category 회원가입, 회원 로그인 Service interface
 * @since 2024.01.30
 */

public interface UsersService {

	/**
	 * 로그인 처리
	 * @param usersVO
	 * @return
	 */
	public UsersVO login(UsersVO usersVO);

	
	/**
	 * 회원 가입
	 * @param usersVO
	 * @return
	 */
	public int join(UsersVO usersVO);


	/**
	 * 회원 가입시 ID 중복체크
	 * @param usersVO
	 * @return
	 */
	public int duplicateIdCheck(UsersVO usersVO);
}
