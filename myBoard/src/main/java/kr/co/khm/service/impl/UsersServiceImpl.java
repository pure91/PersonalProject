package kr.co.khm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.khm.mapper.UsersMapper;
import kr.co.khm.service.UsersService;
import kr.co.khm.vo.UsersVO;

/**
 * @author 김형민
 * @category 회원가입, 회원 로그인 ServiceImpl class
 * @since 2024.01.30
 */

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired UsersMapper usersMapper;
	
	/**
	 * 로그인 처리
	 */
	@Override
	public UsersVO login(UsersVO usersVO) {
		return usersMapper.login(usersVO);
	}
	
	/**
	 * 회원 가입
	 */
	public int join(UsersVO usersVO) {
		return usersMapper.join(usersVO);
	}

}
