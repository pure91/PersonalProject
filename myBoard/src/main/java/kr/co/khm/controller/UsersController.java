package kr.co.khm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.khm.service.UsersService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 김형민
 * @category 회원가입, 회원 로그인 컨트롤러
 * @since 2024.01.30
 */

@Slf4j
@RequestMapping("/users")
@Controller
public class UsersController {
	
	@Autowired UsersService userService;

	public String loginFrom() {
		log.info("login 폼으로 이동");
		return "users/login";
	}
}
